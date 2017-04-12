package com.fykj.product.evaluation.modular.filling.model;

import com.fykj.platform.kernel._c.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liwang on 2017/4/6.
 */
@Entity
@Table(name="t_fill_project")
public class FillProject extends AbstractEntity{
    private static final long serialVersionUID = 8469779736130536608L;
    @Column(name = "name")
    private String name;
    @Column(name = "des")
    private String des;
    @Column(name = "begin_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "status")
    private String status;
    @Column(name = "search_type")
    private int searchType;
    @Column(name = "min_num")
    private int minNum;
    @Column(name = "tem_acc_num")
    private int temAccNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getTemAccNum() {
        return temAccNum;
    }

    public void setTemAccNum(int temAccNum) {
        this.temAccNum = temAccNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
