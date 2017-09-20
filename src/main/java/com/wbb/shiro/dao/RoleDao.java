package com.wbb.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbb.shiro.model.Role;

public interface RoleDao {

	Role selectRoleByRoleId(@Param("role_id")int role_id);
	List<Role> selectAllRoles();
}
