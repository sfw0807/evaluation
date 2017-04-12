package com.fykj.platform.kernel._c;

import javax.servlet.http.HttpServletRequest;

import com.fykj.platform.kernel._c.model.SessionUser;
import com.fykj.platform.util.JUniqueUtils;
import com.fykj.platform.web.filter.ServerSessionFilter;


public class ServerSessionHolder {

	private final static ThreadLocal<ServerSession> THREAD_LOCAL=new ThreadLocal<ServerSession>();

	/**
	 * get the Servlet Session.
	 * @return
	 */
	static ServerSession getServerSession(){
		return THREAD_LOCAL.get();
	}
	
	/**
	 * get current session user information.
	 * @return
	 */
	public static SessionUser getSessionUser(){
		return getServerSession().getSessionUser();
	}
	
	public static boolean isLogin(){
		return getServerSession()!=null
				&&getServerSession().getSessionUser()!=null
				&&getServerSession().getSessionUser()!=DEFUALT;
	}
	
	public static final SessionUser DEFUALT=new SessionUser();
	static{
		DEFUALT.setId("SYS-ID");
		DEFUALT.setNatureName("SYS");
		DEFUALT.setUserName("SYS");
	}
	
	public static final String SESSION_KEY="_FYKJ_SESSION_KEY_"+JUniqueUtils.sequence();
	
	private static final Object sync=new Object();
	
	/**
	 * only call via filter {@link ServerSessionFilter}
	 * @param request
	 * @param serverSession
	 */
	public static final void setServerSession(HttpServletRequest request,ServerSession serverSession){
		if(serverSession==null){
			ServerSession session= (ServerSession) request.getSession().getAttribute(SESSION_KEY);
			if(session==null){
				synchronized (sync) {
					if((session= (ServerSession) request.getSession().getAttribute(SESSION_KEY))==null){
						session=new ServerSession();
						session.setSessionUser(DEFUALT);
						session.setHttpSession(request.getSession());
						request.getSession().setAttribute(SESSION_KEY, session);  // initialize a pure server session #session id
					}
				}
			}
			THREAD_LOCAL.set(session);
		}
		else{
			request.getSession().setAttribute(SESSION_KEY, serverSession);
		}
	}
	
	/**
	 * binding the session user information into the current Servlet Session.
	 * @param sessionUser
	 */
	public static  final void setSessionUser(SessionUser sessionUser ){
		getServerSession().setSessionUser(sessionUser);
	}
	
	/**
	 * remove current session context , the server session is not destroyed until
	 * Servlet Session destroys .
	 * <p>generally the method is called when loginouting the system
	 */
	public static final void removeServerSession(){
		getServerSession().clear();
		getServerSession().setSessionUser(null);
		getServerSession().getHttpSession().removeAttribute(SESSION_KEY);
	}
	
	/**
	 * get the value that stores in Servlet Session.
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		return getServerSession().get(key);
	}
	
	/**
	 * set the value in Servlet Session.
	 * @param key
	 * @param value
	 */
	public static void put(String key,Object value){
		getServerSession().put(key, value);
	}
	
	/**
	 * remove the value from the Servlet Session.
	 * @param key
	 */
	public static void remove(String key){
		getServerSession().remove(key);
	}
	
	
	/**
	 * whether the key exists in the Servlet Session.
	 * @param key
	 * @return
	 */
	public static boolean contains(String key){
		return getServerSession().containsKey(key);
	}
	
	
	
	
}
