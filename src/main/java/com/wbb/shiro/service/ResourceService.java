package com.wbb.shiro.service;

import java.util.List;

import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Tree;
import com.wbb.shiro.model.Url;

/**
 *  资源接口
 * @author wbb
 */
public interface ResourceService {

	public List<Resources> selectAllResources();
	
	public List<Tree> getResourcesTree();
	/**
	 * 获取菜单url
	 * @param username
	 * @return
	 */
	public List<Url> returnUrl(String username);
	/**
	 * 获取权限
	 * @param username
	 * @return
	 */
	public List<String> getPermissons(String username);
}
