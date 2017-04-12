package com.fykj.product.evaluation.modular.filling.model;

import com.fykj.platform.kernel._c.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwang on 2017/4/6.
 * 填报账号
 */
@Entity
@Table(name = "t_fill_account")
public class FillAccount extends AbstractEntity {
    private static final long serialVersionUID = -2529519363326383164L;
    @Column(name = "type")
    private int type;//账号类型 0：学校代码，1：临时账号
    @Column(name = "account")
    private String account;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "fill_project_id")
    private String fillProjectId;
    @Column(name = "eval_target_id")
    private String evalTargetId;
    @Column(name = "status")
    private String status;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getFillProjectId() {
        return fillProjectId;
    }

    public void setFillProjectId(String fillProjectId) {
        this.fillProjectId = fillProjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvalTargetId() {
        return evalTargetId;
    }

    public void setEvalTargetId(String evalTargetId) {
        this.evalTargetId = evalTargetId;
    }
}
