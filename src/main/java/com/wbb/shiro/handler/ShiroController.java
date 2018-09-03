package com.wbb.shiro.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wbb.shiro.model.Resources;
import com.wbb.shiro.model.Role;
import com.wbb.shiro.model.Tree;
import com.wbb.shiro.model.Url;
import com.wbb.shiro.model.User;
import com.wbb.shiro.service.ReService;
import com.wbb.shiro.service.ResourceService;
import com.wbb.shiro.service.RoleService;
import com.wbb.shiro.service.ShiroService;
import com.wbb.shiro.service.UserService;

@Controller
public class ShiroController {
	

	@Resource
	UserService userService;
	@Resource
	ReService reService;
	@Resource
	RoleService roleService;
	@Resource
	ResourceService resourceService;
	@Autowired
	private ShiroService shiroService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request){
		
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
	        	urls=reService.returnUrl(username);
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
		List<Role> roles=shiroService.selectAllRoles();
		System.out.println("----------Role---------------");
		return roles;
	}
	@RequestMapping("/getAllResource")
	@ResponseBody
	public List<Resources> getAllResource(){
		List<Resources> Resources=resourceService.selectAllResource();
		System.out.println("-----------Resources--------------");
		return Resources;
	}
	@RequestMapping("/getAllUsers")
	@ResponseBody
	public List<User> getAllUsers(){
		List<User> users=userService.getAllUsers();
		System.out.println("------------getAllUsers-------------");
		return users;
	}
	@RequestMapping("/getTree")
	@ResponseBody
	public List<Tree> getTree(){
		List<Tree> tree=resourceService.returnTree();
		System.out.println("-----------getTree--------------");
		return tree;
	}

	
}
