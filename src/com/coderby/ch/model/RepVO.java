package com.coderby.ch.model;

public class RepVO {
	private int reporterId;
	private String reporterName;
	private String email;
	private String pressName;
	
	public int getReporterId() {
		return reporterId;
	}
	public void setReporterId(int reporterId) {
		this.reporterId = reporterId;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPressName() {
		return pressName;
	}
	public void setPressName(String pressName) {
		this.pressName = pressName;
	}
	
	@Override
	public String toString() {
		return "RepVO [reporterId=" + reporterId + ", reporterName=" + reporterName + ", email=" + email
				+ ", pressName=" + pressName + "]";
	}
	
	
}
