package com.fykj.product.evaluation.modular.evaluating.mitem.vo;

import com.fykj.platform.kernel._c.model.JModel;

public class MeasureItemCompRate implements JModel {

	/**
	 * measure item id
	 */
	private String id;
	
	private String name;
	
	private String score;

	private String projectId;
	
	private String scoreRate;
	
	private String finalScore;
	
	private String measureId;
	
	private String smScoreRate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(String scoreRate) {
		this.scoreRate = scoreRate;
	}

	public String getMeasureId() {
		return measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	public String getSmScoreRate() {
		return smScoreRate;
	}

	public void setSmScoreRate(String smScoreRate) {
		this.smScoreRate = smScoreRate;
	}
	
	
	
}
