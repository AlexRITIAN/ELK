package com.lenovo.elk3.beans;

public class NotificationBean {
	int id;
	String msg;
	String content;
	int status;
	int delmark;
	String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDelmark() {
		return delmark;
	}

	public void setDelmark(int delmark) {
		this.delmark = delmark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
