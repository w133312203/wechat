package com.hm.carService.domain;

public class ConfigureField {

	private Integer id;
	
	private String fieldName;//字段名称
	
	private Integer type = 0;//0 非标题字段 1 标题字段
	
	private Integer softwareId;//小程序Id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(Integer softwareId) {
		this.softwareId = softwareId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
