package com.wbb.shiro.service;

import java.util.List;

import com.wbb.shiro.model.Role;

/**
 * 角色接口
 * @author wbb
 */
public interface RoleService {

	public Role selectRoleByRoleId(int role_id);
	
	public List<Role> selectAllRoles();
	
}
 