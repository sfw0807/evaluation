package com.fykj.product.evaluation.common.constant;

public interface RptCommonConstants {

	
	String QUERY_TYPE_DETAIL = "1";
	
	String QUERY_TYPE_BRIEF = "0";
	
	int DEFAULT_REQUIRED_STATUS = 0;
	
	int DEFAULT_INPUT_TYPE = 1;
	
	int RPT_TYPE_RPT = 0 ;
	
	int SOURCE_TYPE_OTHER = 0 ;
	
	String REF_TYPE_FILE = "1"; //上传的真实文件
	
	String REF_TYPE_REUSE = "0"; //引用其他文件
	
	String OPT_DETAIL = "1"; //查询选项会带出选项答案和内容
	
	String OPT_BRIEF = "0"; //查询选项不会关联选项内容

	String LOGIN_TYPE_UNICODE = "0"; //统一代码登录

	String LOGIN_TYPE_REAL_ACCOUNT = "1"; //实名登录

	String LOGIN_TYPE_TMP_ACCOUNT = "2"; //临时账号登录

	String  UN_COMMIT_FLAG = "0"; //答卷未提交

	String  COMMIT_FLAG = "1"; //答卷提交


}
