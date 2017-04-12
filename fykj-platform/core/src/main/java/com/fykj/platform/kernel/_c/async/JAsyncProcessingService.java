package com.fykj.platform.kernel._c.async;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author J
 * @param <T>
 */
public class JAsyncProcessingService{
	
	protected final Logger LOGGER=LoggerFactory.getLogger(getClass());
	
	private JAsyncConfig config=null;
	
	private JAsyncProcessingService(JAsyncConfig config){
		this.config=config;
	}
	
	public static JAsyncProcessingService get(){
		JAsyncConfig config=new JAsyncConfig();
		{
			config.setName("default");
		}
		return new JAsyncProcessingService(config);
	}
	
	/**
	 * always re-create more and more threads, you should use <strong> {@link #get()} </strong> instead of 
	 * @param config
	 * @return
	 */
	public static JAsyncProcessingService get(JAsyncConfig config){
		return new JAsyncProcessingService(config);
	}
	
	private ThreadPoolExecutor taskThreadPoolExecutor =null;
	
	private ThreadPoolExecutor getTaskThreadPoolExecutor() {
		if(taskThreadPoolExecutor==null){
			synchronized(this){
				if(taskThreadPoolExecutor==null){
					taskThreadPoolExecutor =new ThreadPoolExecutor
							(config.getFixedThreadCount(), config.getFixedThreadCount(), 30, TimeUnit.SECONDS, 
									new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
										int index=0;
										@Override
										public Thread newThread(Runnable r) {
											return new Thread(r,"queue-element-execute-thread-("+(config.getName())+")-"+(++index));
										}
									},new ThreadPoolExecutor.CallerRunsPolicy());
				}
			}
		}
		return taskThreadPoolExecutor;
	}
	
	public void addExecution(JQueueElement execution,JQueueElementProcessor<? extends JQueueElement> processor){
		QueueElementExecutionRunnable eventExecutionRunnable=new QueueElementExecutionRunnable(execution, processor);
		getTaskThreadPoolExecutor().execute(eventExecutionRunnable);
	}
	
	public void addExecution(Runnable runnable){
		getTaskThreadPoolExecutor().execute(runnable);
	}

	private class QueueElementExecutionRunnable  implements Runnable{
		
		private JQueueElement obj;
		
		private JQueueElementProcessor<? super JQueueElement> processor;
		
		private Exception exception;
		
		private boolean complete;
		
		public boolean isComplete() {
			return complete;
		}
		
		public Exception getException() {
			return exception;
		}

		public void setException(Exception exception) {
			this.exception = exception;
		}

		public QueueElementExecutionRunnable(JQueueElement obj,JQueueElementProcessor<? extends JQueueElement> processor) {
			this.obj=obj;
			this.processor= (JQueueElementProcessor<? super JQueueElement>) processor;
		}
		
		@Override
		public void run() {
			try {
				processor.process(obj);
			} catch (Exception e) {
				exception=e;
			}
			complete=true;
		}
	}
	
}
