package com.fykj.platform.kernel.springjpa.meta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fykj.platform.kernel._c.model.JModel;
import com.fykj.platform.util.JStringUtils;

public class JEntityUtilService
{
	
	private static Map<Class<?>, _EntityMeta> entityMetas=new HashMap<>();

	private Object sync=new Object();
	
	@SuppressWarnings("serial")
	private class _EntityMeta implements JModel{
		JEntityModelMeta entityModelMeta;
		String selectCause;
	}
	
	private JEntityModelMeta genEntityModelMeta(Class<?> entityClass){
		JEntityMetaFinder entityMetaFinder=new JEntityMetaFinder(entityClass);
		JEntityModelMeta entityModelMeta= entityMetaFinder.find();
		return entityModelMeta;
	}
	
	
	private _EntityMeta _getE(Class<?> entityClass){
		if(entityMetas.containsKey(entityClass)){
			return entityMetas.get(entityClass);
		}
		synchronized (sync) {
			if(entityMetas.containsKey(entityClass)){
				return entityMetas.get(entityClass);
			}
			_EntityMeta entityMeta=new _EntityMeta();
			entityMeta.entityModelMeta= genEntityModelMeta(entityClass);
			entityMeta.selectCause=_selectCause(entityMeta.entityModelMeta, "");
			entityMetas.put(entityClass, entityMeta);
			return entityMeta;
		}
	 
	}
	
	public JEntityModelMeta getEntityModelMeta(Class<?> entityClass){
		return _getE(entityClass).entityModelMeta;
	}
	
	private String _selectCause(Class<?> entityClass,String alias){
		JEntityModelMeta entityModelMeta=getEntityModelMeta(entityClass);
		return _selectCause(entityModelMeta, alias);
	}

	private String _selectCause(JEntityModelMeta entityModelMeta, String alias) {
		Collection<JEntityColumnMeta> entityColumnMetas=entityModelMeta.columnMetas();
		String selectCause="select ";
		for(JEntityColumnMeta columnMeta:entityColumnMetas){
			selectCause=selectCause
					+(JStringUtils.isNullOrEmpty(alias)?"":(alias+"."))
					+columnMeta.getProperty()
					+" as "+columnMeta.getProperty()+" ,";
		}
		selectCause=selectCause.substring(0, selectCause.lastIndexOf(","));
		return selectCause;
	}
	
	public String selectCause(Class<?> entityClass,String... alias){
		return alias.length==0?(_getE(entityClass).selectCause):(_selectCause(entityClass, alias[0]));
	}

	private static final JEntityUtilService INSTANCE=new JEntityUtilService();
	
	public static JEntityUtilService get(){
		return INSTANCE;
	}
	
	
	

}
