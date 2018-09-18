package com.wbb.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Role;
import com.wbb.shiro.persistence.ResourceMapper;
import com.wbb.shiro.persistence.RoleMapper;
import com.wbb.shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	RoleMapper roleDao;	
	@Resource
	ResourceMapper resourceMapper;
	
	public Role selectRoleByRoleId(int role_id){
		return roleDao.selectRoleByRoleId(role_id);
	}
	public List<Role> selectAllRoles(){
		List<Role> roles=roleDao.selectAllRoles();
		List<Resources> resources=resourceMapper.selectAllResources();
		for(Role role:roles){
			String[] re_id=role.getResource_id().split(",");
			StringBuffer re_id1=new StringBuffer();
			for(int i=0;i<re_id.length;i++){
				for(Resources re:resources){
					if(Integer.parseInt(re_id[i])==re.getRe_id()){
						re_id1.append(re.getRe_name()+",");
					}
				}
			}
			role.setResource_id(re_id1.toString());
		}
		return roles;
	}
	
}
 