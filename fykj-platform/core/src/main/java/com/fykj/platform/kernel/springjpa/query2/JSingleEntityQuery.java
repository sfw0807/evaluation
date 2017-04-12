package com.fykj.platform.kernel.springjpa.query2;


import java.util.List;

import javax.persistence.EntityManager;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.JPageable;
import com.fykj.platform.kernel.springjpa.query2.JCondition.LinkType;

public class JSingleEntityQuery {
	
	private JSingleEntityQueryMeta singleEntityQueryMeta;
	
	private EntityManager entityManager;

	public JSingleEntityQuery(Class<?> entityClass,
			EntityManager entityManager) {
		this.singleEntityQueryMeta = new JSingleEntityQueryMeta(entityClass,this);
		this.entityManager = entityManager;
	}
	
	public JCondition condition(){
		return singleEntityQueryMeta.condition();
	}
	
	public JCondition conditionDefault(){
		return singleEntityQueryMeta.conditionDefault();
	}
	
	/**
	 * @param id  primary id 
	 * @return
	 */
	public JCondition active(String id){
		return singleEntityQueryMeta.conditionDefault()
				.primary(id, LinkType.AND);
	}
	
	public JOrder order() {
		return singleEntityQueryMeta.order();
	}
	
	public <T> List<T> models(){
		return models(null);
	}
	
	public <T> List<T> models(Class<T> clazz){
		return JQueryBuilder.get(entityManager).jpqlQuery()
		.setJpql(
				(clazz==null?" ":(singleEntityQueryMeta.toSelectCause()+" "))
				+singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.models();
	}
	
	public <T> T model(Class<T> clazz){
		return JQueryBuilder.get(entityManager).jpqlQuery()
		.setJpql(
				(clazz==null?" ":(singleEntityQueryMeta.toSelectCause()+" "))
				+singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.model();
	}
	public <T> T model(){
		return model(null);
	}
	
	public long count(){
		return JQueryBuilder.get(entityManager).jpqlQuery()
				.setJpql("select count("+JSingleEntityQueryMeta.ALIAS+") "+singleEntityQueryMeta.toJPQL())
				.setParams(singleEntityQueryMeta.toParams())
				.model();
	}
	
	public <T> JPage<T> modelPage(JPageable pageable,Class<T> clazz){
		return JQueryBuilder.get(entityManager).jpqlQuery()
		.setJpql(
				(clazz==null?" ":(singleEntityQueryMeta.toSelectCause()+" "))
				+singleEntityQueryMeta.toJPQL())
		.setParams(singleEntityQueryMeta.toParams())
		.setPageable(pageable)
		.modelPage();
	}
	
	public <T> JPage<T> modelPage(JPageable pageable){
		return modelPage(pageable, null);
	}
	
}
