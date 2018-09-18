package com.wbb.shiro.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wbb.shiro.model.Role;

public interface RoleMapper {

	Role selectRoleByRoleId(@Param("role_id")int role_id);
	List<Role> selectAllRoles();
}
