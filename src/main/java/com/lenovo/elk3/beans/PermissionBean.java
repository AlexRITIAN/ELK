package com.lenovo.elk3.beans;

public class PermissionBean {
	int id;
	String code;
	String name;
	String moduleUrl;
	String operationUrl;
	int orderNo;
	String createTime;
	String editTime;
	int pid;
	String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public String getOperationUrl() {
		return operationUrl;
	}
	public void setOperationUrl(String operationUrl) {
		this.operationUrl = operationUrl;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "PermissionBean [id=" + id + ", code=" + code + ", name=" + name + ", moduleUrl=" + moduleUrl
				+ ", operationUrl=" + operationUrl + ", orderNo=" + orderNo + ", createTime=" + createTime
				+ ", editTime=" + editTime + ", pid=" + pid + ", remark=" + remark + "]";
	}
	
	
	
};
