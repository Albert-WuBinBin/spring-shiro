package com.wbb.shiro.service;

import java.util.List;

import com.wbb.shiro.model.User;

/**
 * 用户接口
 * @author wbb
 */
public interface UserService {
	
	public User selectByName(String username);
	
	public List<User> getAllUsers();
	
}
