package com.wbb.shiro.model;

import java.util.List;

public class Role {

	private int role_id;
	private String  role_name;
	private String description;
	private String resource_id;
						 
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name + ", description=" + description
				+ ", resource_id=" + resource_id + "]";
	}
	
	
}
