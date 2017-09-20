package com.wbb.shiro.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wbb.shiro.dao.ResourceDao;
import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Tree;

@Service
public class ResourceService {

	@Resource
	ResourceDao resourceDao;
	
	public List<Resources> selectAllResource(){
		return resourceDao.selectAllResource();
	}
	public List<Tree> returnTree(){
		List<Tree> list=new ArrayList<>();
		List<Resources>resources= resourceDao.selectAllResource();
		for(Resources r:resources){
			list.add(new Tree(r.getRe_id(),r.getParent_id(),r.getRe_name()));
		}
		return list;
	}
	
}
