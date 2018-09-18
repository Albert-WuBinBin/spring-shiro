package com.wbb.shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Role;
import com.wbb.shiro.model.Tree;
import com.wbb.shiro.model.Url;
import com.wbb.shiro.model.User;
import com.wbb.shiro.persistence.ResourceMapper;
import com.wbb.shiro.persistence.RoleMapper;
import com.wbb.shiro.persistence.UserMapper;
import com.wbb.shiro.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Resource
	ResourceMapper resourceMapper;
	@Resource
	UserMapper userMapper;
	@Resource
	RoleMapper roleMapper;
	
	public List<Resources> selectAllResources(){
		return resourceMapper.selectAllResources();
	}
	public List<Tree> getResourcesTree(){
		List<Tree> list=new ArrayList<>();
		List<Resources>resources= resourceMapper.selectAllResources();
		for(Resources r:resources){
			list.add(new Tree(r.getRe_id(),r.getParent_id(),r.getRe_name()));
		}
		return list;
	}
	public List<Url> returnUrl(String username){
		User user=userMapper.selectByName(username);
		Role role=roleMapper.selectRoleByRoleId(user.getRole_id());

		List<Resources> resources=resourceMapper.selectAllResources();
		
		String[] resource_id=role.getResource_id().split(",");
		
		List<Url> urls=new ArrayList<>();
		
		for(Resources r:resources){
			for(int i=0;i<resource_id.length;i++){
				if(Integer.parseInt(resource_id[i])==r.getRe_id()){
					if(r.getType().equals("menu")){
						urls.add(new Url(r.getRe_name(),r.getUrl()));
						break;
					}
					else{
						for(Resources r1:resources){
							if(r.getParent_id()==r1.getRe_id()){
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
	public List<String> getPermissons(String username){
		User user=userMapper.selectByName(username);
		Role role=roleMapper.selectRoleByRoleId(user.getRole_id());
		
		List<String> permissions=new ArrayList<>();
		
		List<Resources> resources=resourceMapper.selectAllResources();
		
		String[] resource_id=role.getResource_id().split(",");
	
		for(Resources r:resources){
			for(int i=0;i<resource_id.length;i++){
				
				if(Integer.parseInt(resource_id[i])==r.getRe_id()){
					permissions.add(r.getPermission());
					break;
				}
			}
		}
		return permissions;
	}
}
