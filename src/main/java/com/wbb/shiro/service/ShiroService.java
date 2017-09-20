package com.wbb.shiro.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wbb.shiro.dao.RoleDao;
import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Role;


public class ShiroService {

	@RequiresRoles({"admin"})
	public void testAn(){
		System.err.println("Data:"+new Date());
	}
	@Resource
	RoleDao roleDao;	
	@Resource
	ResourceService resourceService;
	public Role selectRoleByRoleId(int role_id){
		return roleDao.selectRoleByRoleId(role_id);
	}
	@RequiresPermissions({"role:select"})
	public List<Role> selectAllRoles(){
		System.err.println("role:select");
		List<Role> roles=roleDao.selectAllRoles();
		List<Resources> resources=resourceService.selectAllResource();
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
