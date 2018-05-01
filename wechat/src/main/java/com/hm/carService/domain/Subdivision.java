package com.hm.carService.domain;

public class Subdivision {

	private Integer id;
	
	private String carName;//名称
	
	private String price;//现价
	
	private String style;//款式
	
	private String guidancePrice;//指导价
	
	private Integer carId;//小程序Id
	
	private String describes;//描述
	
	private String horsepower;//马力
	
	private Double barePrice;//裸车价
	
	private Integer displacement;//0 1.0L(含)以下 1 1.0L-1.6L(含) 2 1.6L-2.0L(含) 3 2.0L-2.5L(含) 4 2.5L-3.0L(含) 5 3.0L-4.0L(含) 6 4.0L以上
	
	private Integer carType;//0 国产 1 进口
	
	private Integer seatNum;//座位数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getGuidancePrice() {
		return guidancePrice;
	}

	public void setGuidancePrice(String guidancePrice) {
		this.guidancePrice = guidancePrice;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(String horsepower) {
		this.horsepower = horsepower;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Double getBarePrice() {
		return barePrice;
	}

	public void setBarePrice(Double barePrice) {
		this.barePrice = barePrice;
	}

	public Integer getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Integer displacement) {
		this.displacement = displacement;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	
}
