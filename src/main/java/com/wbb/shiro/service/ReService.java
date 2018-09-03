package com.wbb.shiro.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Role;
import com.wbb.shiro.model.Url;
import com.wbb.shiro.model.User;

@Service
public class ReService {

	@Resource
	UserService userService;
	@Resource
	RoleService roleService;
	@Resource
	ResourceService resourceService;
	
	public List<Url> returnUrl(String username){
		User user=userService.selectByName(username);
		Role role=roleService.selectRoleByRoleId(user.getRole_id());

		List<Resources> resources=resourceService.selectAllResource();
		String[] resource_id=role.getResource_id().split(",");
		
		List<Url> urls=new ArrayList<>();
		
		for(Resources r:resources){
			for(int i=0;i<resource_id.length;i++){
				if(Integer.parseInt(resource_id[i])==r.getRe_id()){
					
					if(r.getType().equals("menu")){
						System.err.println(r.getPermission());
						urls.add(new Url(r.getRe_name(),r.getUrl()));
						break;
					}
					else{
						for(Resources r1:resources){
							if(r.getParent_id()==r1.getRe_id()){
								System.err.println(r.getPermission());
								urls.add(new Url(r1.getRe_name(),r1.getUrl()));
								break;
							}
						}
						
					}
				}
			}
		}
		return urls;
	}
	public List<String> returnPerms(String username){
		User user=userService.selectByName(username);
		Role role=roleService.selectRoleByRoleId(user.getRole_id());
		
		List<String> permissions=new ArrayList<>();
		
		List<Resources> resources=resourceService.selectAllResource();
		
		String[] resource_id=role.getResource_id().split(",");
	
		for(Resources r:resources){
			for(int i=0;i<resource_id.length;i++){
				
				if(Integer.parseInt(resource_id[i])==r.getRe_id()){
					System.out.println("授权："+r.getPermission());
					permissions.add(r.getPermission());
					break;
				}
			}
		}
		return permissions;
	}
}
