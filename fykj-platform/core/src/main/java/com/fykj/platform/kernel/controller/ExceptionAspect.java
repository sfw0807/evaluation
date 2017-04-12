package com.fykj.platform.kernel.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.InvokeResult;

@Aspect
@Component
public class ExceptionAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);  
	
	@Pointcut("execution(@org.springframework.web.bind.annotation.RequestMapping * *(..))"
			+ "&& !@annotation(com.fykj.platform.kernel.controller.NoClosureException)"
			)
	public void exception() {
	}
	
	@Around(value = "exception()")
	public Object aroundLog(ProceedingJoinPoint pjd) throws Throwable {
		try {
			return pjd.proceed();
		}catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		}catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		}catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
