package com.fykj.platform.kernel._c.model;

import java.util.List;

public  abstract class JPageUtil {

	public static void replaceConent(JPage<?> page,  List<?> content){
		if(page instanceof JImpl<?>){
			JImpl<?> simplePageImpl=(JImpl<?>)page;
			simplePageImpl.setContent(content);
		}
	}
	
	static public JPage wrap(List content){
		SimplePageRequest simplePageRequest=new SimplePageRequest(0, content.size());
		JImpl page=new JImpl();
		JPageUtil.replaceConent(page, content);
		page.setTotalRecordNumber(content.size());
		page.setTotalPageNumber(0);
		page.setPageable(simplePageRequest);
		return page;
	}
	
	
	
}
