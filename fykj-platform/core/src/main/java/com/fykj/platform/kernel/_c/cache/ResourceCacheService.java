/**
 * 
 */
package com.fykj.platform.kernel._c.cache;




/**
 * system resource interface.
 * @author J
 */
public interface ResourceCacheService<T> extends InitialResource,JCacheService{
	
	IdentifierGenerator generator();
	
}
