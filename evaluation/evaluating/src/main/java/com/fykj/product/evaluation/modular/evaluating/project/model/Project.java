package com.fykj.product.evaluation.modular.evaluating.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fykj.platform.kernel._c.model.AbstractEntity;

@Entity
@Table(name = "t_project")
public class Project extends AbstractEntity {

	@Column(name = "name")
	private String name;
	
	@Column(name = "start_date" , updatable = false)
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date startDate;
	
	@Column(name = "end_date" , updatable = false)
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date endDate;
	
	@Column(name = "score")
	private double score;
	
	@Column(name = "flowid")
	private String flowId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "dic_location")
	private String dicLocation;
	
	@Column(name = "self_eval")
	private String selfEval;
	
	@Column(name = "selfeval_start_date" , updatable = false)
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date selfEvalStartDate;
	
	@Column(name = "selfeval_end_date" , updatable = false)
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date selfEvalEndDate; 
	
	@Column(name = "expert_eval")
	private String expertEval;
	
	@Column(name = "expertval_start_date" , updatable = false)
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date expertEvalStartDate;
	
	@Column(name = "experteval_end_date" , updatable = false)
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date expertEvalEndDate; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDicLocation() {
		return dicLocation;
	}

	public void setDicLocation(String dicLocation) {
		this.dicLocation = dicLocation;
	}

	public String getSelfEval() {
		return selfEval;
	}

	public void setSelfEval(String selfEval) {
		this.selfEval = selfEval;
	}

	public Date getSelfEvalStartDate() {
		return selfEvalStartDate;
	}

	public void setSelfEvalStartDate(Date selfEvalStartDate) {
		this.selfEvalStartDate = selfEvalStartDate;
	}

	public Date getSelfEvalEndDate() {
		return selfEvalEndDate;
	}

	public void setSelfEvalEndDate(Date selfEvalEndDate) {
		this.selfEvalEndDate = selfEvalEndDate;
	}

	public String getExpertEval() {
		return expertEval;
	}

	public void setExpertEval(String expertEval) {
		this.expertEval = expertEval;
	}

	public Date getExpertEvalStartDate() {
		return expertEvalStartDate;
	}

	public void setExpertEvalStartDate(Date expertEvalStartDate) {
		this.expertEvalStartDate = expertEvalStartDate;
	}

	public Date getExpertEvalEndDate() {
		return expertEvalEndDate;
	}

	public void setExpertEvalEndDate(Date expertEvalEndDate) {
		this.expertEvalEndDate = expertEvalEndDate;
	}
	
	
}
