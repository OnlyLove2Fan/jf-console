package com.atguigu.jf.console.item.bean.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class IcItemEvaluation {
	private Long evaluationId;

	private String userNickname;

	private String mathShortName;

	private String evaluationContent;

	private BigDecimal evaluationGrade;

	private String itemPicUrl;

	private String shopShortName;

	private Date evaluationTime;

	private Short evaluationStatus;

	private Short dataState;

	private String itemPicName;

	private String itemName;

	private Date modifyTime;

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getMathShortName() {
		return mathShortName;
	}

	public void setMathShortName(String mathShortName) {
		this.mathShortName = mathShortName;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public BigDecimal getEvaluationGrade() {
		return evaluationGrade;
	}

	public void setEvaluationGrade(BigDecimal evaluationGrade) {
		this.evaluationGrade = evaluationGrade;
	}

	public String getItemPicUrl() {
		return itemPicUrl;
	}

	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}

	public String getShopShortName() {
		return shopShortName;
	}

	public void setShopShortName(String shopShortName) {
		this.shopShortName = shopShortName;
	}

	public Date getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public Short getEvaluationStatus() {
		return evaluationStatus;
	}

	public void setEvaluationStatus(Short evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}

	public Short getDataState() {
		return dataState;
	}

	public void setDataState(Short dataState) {
		this.dataState = dataState;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getItemPicName() {
		return itemPicName;
	}

	public void setItemPicName(String itemPicName) {
		this.itemPicName = itemPicName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}