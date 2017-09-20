package com.wbb.shiro.model;

public class Tree {
	private int id;
	private int pId;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tree(int id, int pId, String name) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
	}
	public Tree() {
		super();
	}
	
}
