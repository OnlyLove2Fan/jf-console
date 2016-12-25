package com.atguigu.jf.console.user.bean.bo;

public class SysFuncBeanTree {

	private Long funcId;

	private Long supFuncId;

	private String name;

	private Short dataState;

	private String checked;

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public Long getSupFuncId() {
		return supFuncId;
	}

	public void setSupFuncId(Long supFuncId) {
		this.supFuncId = supFuncId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getDataState() {
		return dataState;
	}

	public void setDataState(Short dataState) {
		this.dataState = dataState;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}