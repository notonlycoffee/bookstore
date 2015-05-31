package cn.itcast.domain;

import java.util.Date;

public class Dbbak {

	private String id;
	private String filename;
	private Date baktime;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getBaktime() {
		return baktime;
	}
	public void setBaktime(Date baktime) {
		this.baktime = baktime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
