package com.wbb.shiro.realms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wbb.shiro.model.Role;
import com.wbb.shiro.model.User;
import com.wbb.shiro.service.ResourceService;
import com.wbb.shiro.service.RoleService;
import com.wbb.shiro.service.UserService;

public class MyRealm extends AuthorizingRealm {
	
	Logger log=LoggerFactory.getLogger(MyRealm.class);
	@Resource
	UserService userService;
	@Resource
	RoleService roleService;
	@Resource
	ResourceService resourceService;
	
	/**
	 * 认证：登录的时候使用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		log.info("认证");
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		User user=userService.selectByName(username);
		if(user == null) {
			throw new UnknownAccountException("用户名不存在");
		}
		Object principal = username;		
		String credentials = user.getPassword();
		String realmName = getName();
		log.info("realmName:"+realmName);
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = null; 
		info = new SimpleAuthenticationInfo(principal, credentials,credentialsSalt, realmName);
		return info;
	}
	/**
	 * 授权：用到权限的时候调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		log.info("授权");
		Object principal = principals.getPrimaryPrincipal();
		
		Set<String> roles=new HashSet<>();
		User user=userService.selectByName(principal.toString());
		Role role=roleService.selectRoleByRoleId(user.getRole_id());
	
		roles.add(role.getRole_name());
		System.err.println(role.toString());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		List<String> permissions=resourceService.getPermissons(principal.toString());
		
		info.addStringPermissions(permissions);
		
		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");;
		int hashIterations = 1024;
		System.out.println(salt);
		Object result = new SimpleHash(hashAlgorithmName, credentials,salt, hashIterations);
		System.out.println(result);
	}
}
