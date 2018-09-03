package com.wbb.shiro.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wbb.shiro.dao.UserDao;
import com.wbb.shiro.model.User;

@Service
public class UserService {

	@Resource
	UserDao userDao;
	
	public List<String> getAllUserNames(){
		return userDao.getAllUserNames();
	}
	public User selectByName(String username){
		return userDao.selectByName(username);
	}
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
}
