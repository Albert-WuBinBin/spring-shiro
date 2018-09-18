package com.wbb.shiro.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbb.shiro.model.User;

public interface UserMapper {

	List<String> getAllUserNames();
	User selectByName(@Param("username")String username);
	List<User> getAllUsers();
}

