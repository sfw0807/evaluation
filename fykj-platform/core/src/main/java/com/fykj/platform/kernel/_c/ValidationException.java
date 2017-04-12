package com.fykj.platform.kernel._c;

import com.fykj.platform.kernel.BusinessException;

/**
 * for any case the developers themselves throw expected validation exception.
 * @author JIAZJ
 *
 */
public class ValidationException extends BusinessException {

	public ValidationException(String message){
		super(message);
	}
	
	public ValidationException(Exception e){
		super(e);
	}
	
}
