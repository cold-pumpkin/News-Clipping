package com.coderby.ch.model;

import java.sql.Date;

public class NewsVO {
	private int newsId;
	private String pressName;
	private String title;
	private String reporterName;
	private String topic;
	private Date newsDate;
	private int hits;
	private int comments;
	private int likes;
	private String link;
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getPressName() {
		return pressName;
	}
	public void setPressName(String pressName) {
		this.pressName = pressName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "NewsVO [newsId=" + newsId + ", pressName=" + pressName + ", title=" + title + ", reporterName="
				+ reporterName + ", topic=" + topic + ", newsDate=" + newsDate + ", hits=" + hits + ", comments="
				+ comments + ", likes=" + likes + ", link=" + link + "]";
	}
	
	

	
	

}
