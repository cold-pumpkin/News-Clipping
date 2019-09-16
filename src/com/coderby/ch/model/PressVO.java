package com.coderby.ch.model;

import java.sql.Date;

public class PressVO {
	private String pressId;
	private String pressName;
	private Date pressDate;
	private String address;
	private int employees;
	
	public String getPressId() {
		return pressId;
	}
	public void setPressId(String pressId) {
		this.pressId = pressId;
	}
	public String getPressName() {
		return pressName;
	}
	public void setPressName(String pressName) {
		this.pressName = pressName;
	}
	public Date getPressDate() {
		return pressDate;
	}
	public void setPressDate(Date pressDate) {
		this.pressDate = pressDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getEmployees() {
		return employees;
	}
	public void setEmployees(int employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		return "PressVO [pressId=" + pressId + ", pressName=" + pressName + ", pressDate=" + pressDate + ", address="
				+ address + ", employees=" + employees + "]";
	}
	

}
