package com.lenovo.elk3.beans;

public class UserRoleRelationBean {
	int id;
	int user_id;
	int role_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "UserRoleRelationBean [id=" + id + ", user_id=" + user_id + ", role_id=" + role_id + "]";
	}

}
