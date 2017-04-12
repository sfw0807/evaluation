package com.fykj.product.evaluation.modular.filling.vo;

import com.fykj.common.excel.annotation.ExcelColumn;
import com.fykj.common.excel.annotation.ExcelHeader;
import com.fykj.common.excel.annotation.ExcelWarning;

/**
 * Created by liwang on 2017/4/10.
 */
@ExcelHeader(headerName = "账号信息")
//@ExcelWarning(warningInfo = {"警告信息"})
public class FillAccountExpVO {

    @ExcelColumn(columnName="学校类型")
    private String targetType;

    @ExcelColumn(columnName="学校名称")
    private String schName;

    @ExcelColumn(columnName="网址")
    private String netAddr;

    @ExcelColumn(columnName="登录账号")
    private String account;

    @ExcelColumn(columnName="密码")
    private String pwd;

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getNetAddr() {
        return netAddr;
    }

    public void setNetAddr(String netAddr) {
        this.netAddr = netAddr;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
