package com.hm.domain;

public class Software {

	private Integer id;
	
	private Integer type;//小程序类型 0 4S店
	
	private Integer euserId;//企业用户Id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getEuserId() {
		return euserId;
	}

	public void setEuserId(Integer euserId) {
		this.euserId = euserId;
	}
	
}
