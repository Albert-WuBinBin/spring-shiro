package com.wbb.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbb.shiro.model.User;

public interface UserDao {

	List<String> getAllUserNames();
	User selectByName(@Param("username")String username);
	List<User> getAllUsers();
}

