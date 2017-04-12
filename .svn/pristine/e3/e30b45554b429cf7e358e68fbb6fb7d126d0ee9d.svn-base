package com.fykj.product.evaluation.modular.filling.vo;

import com.fykj.platform.kernel._c.model.JInputModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Erik_Yim on 2017/4/11.
 */
public class RptLoginVO implements JInputModel {

    private static final long serialVersionUID = -2258011322631642467L;

    @NotNull(message = "用户账号不允许为空!")
    @NotEmpty(message = "用户账号不允许为空!")
    private String userAccount;

    private String password;

    private String loginType;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
