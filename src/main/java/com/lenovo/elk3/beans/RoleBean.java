package com.lenovo.elk3.beans;

public class RoleBean {

	int id;
	String code;
	String name;
	String remark;
	String createTime;
	String editTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	@Override
	public String toString() {
		return "RoleBean [id=" + id + ", code=" + code + ", name=" + name + ", remark=" + remark + ", createTime="
				+ createTime + ", editTime=" + editTime + "]";
	}

}
