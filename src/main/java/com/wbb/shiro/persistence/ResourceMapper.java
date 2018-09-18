 package com.wbb.shiro.persistence;

import java.util.List;

import com.wbb.shiro.model.Resources;

public interface ResourceMapper {

	List<Resources> selectAllResources();
	
}
