package com.fykj.platform.kernel.springjpa.meta;

public interface JFinder<T> {

	/**
	 * get all resource informations fist time
	 * @return
	 */
	public T find();
	
}
