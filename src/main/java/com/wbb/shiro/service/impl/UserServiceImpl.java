package com.wbb.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wbb.shiro.model.User;
import com.wbb.shiro.persistence.UserMapper;
import com.wbb.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserMapper UserMapper;
	
	public User selectByName(String username){
		return UserMapper.selectByName(username);
	}
	public List<User> getAllUsers(){
		return UserMapper.getAllUsers();
	}
	
}
