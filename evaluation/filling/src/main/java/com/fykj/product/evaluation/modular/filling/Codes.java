package com.fykj.product.evaluation.modular.filling;

public class Codes {

	public static class FillProject{
		
		// code type
		public static class STATUS{
			public static final String TYPE_NAME="FILL_PROJECT_STATUS";
			public static final String NEW="NEW";
			public static final String PUBLISH="PUBLISH";
			public static final String END="END";
		}
		public static class SEARCH_TYPE{
			public static final String TYPE_NAME = "SEARCH_TYPE";
			public static final int TRUE_ACCOUNT = 0;
			public static final int UN_ACCOUNT = 1;
			public static final int TEMP_ACCOUNT = 2;
			public static final int NO_ACCOUNT = 3;
		}
	}
	public static class COMMON{
		public static final String EVAL_TARGET_PERSION_TYPE = "PERS";
	}
}
