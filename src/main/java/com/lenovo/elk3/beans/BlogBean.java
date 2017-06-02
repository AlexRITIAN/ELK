package com.lenovo.elk3.beans;

public class BlogBean {
	// private String index;
	// private String type;
	private String title;
	private String content_show;
	private String content_search;
	private String date;
	private String description;
	private String author;
	private String source_object;
	private String tag;
	private String category;

	public BlogBean() {
		title = "default";
		content_show = "default";
		description = "default";
		source_object = "default";
		tag = "default";
		category = "default";
		author = "admin";
	}

	public String getContent_search() {
		return content_search;
	}

	public void setContent_search(String content_search) {
		this.content_search = content_search;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSource_object() {
		return source_object;
	}

	public void setSource_object(String source_object) {
		this.source_object = source_object;
	}

	/*
	 * public String getIndex() { return index; }
	 * 
	 * public void setIndex(String index) { this.index = index; }
	 * 
	 * public String getType() { return type; }
	 * 
	 * public void setType(String type) { this.type = type; }
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent_show() {
		return content_show;
	}

	public void setContent_show(String content_show) {
		this.content_show = content_show;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
