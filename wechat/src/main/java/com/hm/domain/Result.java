package com.hm.domain;

public class Result {
  private String days; //关注时间
  private Integer count1;//新用户取消关注
  private Integer count2;//新用户关注 
  private Integer count3;//老用户取消关注
  private Integer count4;//老用户关注
public String getDays() {
	return days;
}
public void setDays(String days) {
	this.days = days;
}
public Integer getCount1() {
	return count1;
}
public void setCount1(Integer count1) {
	this.count1 = count1;
}
public Integer getCount2() {
	return count2;
}
public void setCount2(Integer count2) {
	this.count2 = count2;
}
public Integer getCount3() {
	return count3;
}
public void setCount3(Integer count3) {
	this.count3 = count3;
}
public Integer getCount4() {
	return count4;
}
public void setCount4(Integer count4) {
	this.count4 = count4;
}
}
