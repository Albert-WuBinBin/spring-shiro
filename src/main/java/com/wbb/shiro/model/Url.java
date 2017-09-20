package com.wbb.shiro.model;

public class Url {

	private String re_name;
	private String url;
	public String getRe_name() {
		return re_name;
	}
	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Url(String re_name, String url) {
		super();
		this.re_name = re_name;
		this.url = url;
	}
	public Url() {
		super();
	}
	
}
