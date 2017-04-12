/**
 * 
 */
package com.fykj;

/**
 * @author zhengzw
 *
 */
public interface CodesTable {
	
	// 用户状态
	public interface UserState {
		String code = "USER_DISABLED";
		String enable = "1";  // 启用
		String disable = "0"; // 禁用
	}
	
	
	// 树节点选择状态
	public interface CheckState {
		String selected = "SELECTED"; // 全选
		String undetermined = "UNDETERMINED"; // 半选
	}
	
	// 职位
	public interface Title {
		String code = "title";
		String pm = "PM";
		String spm = "SPM";
		String dm = "DM";
	}
	
	// 性别
	public interface Gender {
		String code = "gender";
		
		String male = "male";
		
		String female = "female";
	}
	
	// 是否生成账号
	public interface GenerateAccount {
		String code = "generateAccount";
		
		String yes = "1";
		String no = "0";
	}
	
	// 项目类型
	public interface ProjectType {
		String code = "projectType";
		
		String yy = "01";  // 运营
		String dj = "02";  // 代建
		String zx = "03";  // 咨询
		String wb = "04";  // 外包
		String yw = "05";  // 运维
	}
	
	// 项目资源申请状态
	public interface ProjectResourceStatus {
		String code = "projectResourceStatus";
		
		// 申请中
		String processing = "processing";
		// 项目中
		String projecting = "projecting";
		// 已释放
		String projectout = "projectout";
		// 已预订
		String reserved = "reserved";
	}
	
	// leixing
	public interface InCome {
		String code = "income";
	
		String est = "est";
		String rel = "rel";
	}
	
	// 项目审核标识
	public interface Approve {
		String code = "approve";
		
		String saving = "0";
		String approving = "1";
		String approved = "2";
		String backing = "3";
	}
	
	// 人员安排状态
	public interface PlanStatus {
		String code = "planstatus";
		
		String plan = "0";
		String unplan = "1";
	}
	
	// 是否是新客户
	public interface IsNewCustomer {
		String code = "isNewCustomer";
		
		String yes = "1";
		
		String no = "0";
	}
	
	// 支付周期
	public interface Payment {
		String code = "payment";
		
		String milestone = "0";  // 里程碑
		String per_month = "1"; // 每月
		String every_two_month = "2"; // 每两个月
		String quarter_year = "3";  // 年
	}
	
	// 帐期
	public interface Paydays {
		String code = "paydays";
		
		String less_than_30 = "0";
		String between_31_45 = "1";
		String between_46_60 = "2";
		String between_61_90 = "3";
		String more_then_90 = "4";
 	}
	
	// 费用结算类型
	public interface Bill {
		String code = "bill";
		
		String fixed_price = "0";  // 项目为固定价格
		String un_fixed_price = "1";  // 项目为不固定价格，结算按照工作量计算
		String per_day_with_top = "2"; // 按人天计价，项目金额有封顶值
		String per_day_without_top = "3"; // 按人天计价，项目金额无封顶值
		String fix_price_per_month = "4"; // 每月固定价格
	}
}
