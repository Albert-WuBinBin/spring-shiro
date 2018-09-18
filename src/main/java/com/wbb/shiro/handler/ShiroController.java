package com.wbb.shiro.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Role;
import com.wbb.shiro.model.Tree;
import com.wbb.shiro.model.Url;
import com.wbb.shiro.model.User;
import com.wbb.shiro.service.ResourceService;
import com.wbb.shiro.service.RoleService;
import com.wbb.shiro.service.UserService;

@Controller
public class ShiroController {
	
	private Logger logger = LoggerFactory.getLogger(ShiroController.class);

	@Resource
	UserService userService;
	@Resource
	RoleService roleService;
	@Resource
	ResourceService resourceService;
	@Resource
	HttpServletRequest request;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(){
		
		System.out.println("login");
		String username = request.getParameter("username");  
	    String password = request.getParameter("password"); 
	    List<Url> urls=new ArrayList<>();
	    HttpSession session=request.getSession(true);
	    UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
	    Subject subject = SecurityUtils.getSubject();  
	    try {  
	        subject.login(token);  
	        if (subject.isAuthenticated()) {  
	        	session.setAttribute("username", username);
	        	urls=resourceService.returnUrl(username);
	        	session.setAttribute("url",urls);
	            return "success";  
	        } else {  
	            return "login";  
	        }  
	    } catch (Exception e) {  
	      session.setAttribute("message", "wrong");
	      e.printStackTrace(); 
	      return "login";  
	    }
	   
	}
	
	
	@RequestMapping("/getAllRoles")
	@ResponseBody
	public List<Role> getAllRoles(){
		List<Role> roles=roleService.selectAllRoles();
		logger.info("roles:"+roles);
		return roles;
	}
	@RequestMapping("/getAllResource")
	@ResponseBody
	public List<Resources> getAllResource(){
		List<Resources> resources=resourceService.selectAllResources();
		logger.info("roles:"+resources);
		return resources;
	}
	@RequestMapping("/getAllUsers")
	@ResponseBody
	public List<User> getAllUsers(){
		List<User> users=userService.getAllUsers();
		logger.info("users:"+users);
		return users;
	}
	@RequestMapping("/getTree")
	@ResponseBody
	public List<Tree> getTree(){
		List<Tree> tree=resourceService.getResourcesTree();
		return tree;
	}
}
