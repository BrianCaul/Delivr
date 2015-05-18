package com.delivr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.delivr.model.Driver;
import com.delivr.model.User;
import com.delivr.model.UserLogin;
import com.delivr.service.CustomerService;
import com.delivr.service.DriverService;
import com.delivr.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private CustomerService customerService;
	
		
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();		
		model.addAttribute("user", user);		
		return "signup";
	}
		 
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {		
		if(result.hasErrors()) {
			return "signup";
		} else if(userService.findByUserName(user.getUserName())) {
			model.addAttribute("message", "User Name exists. Try another user name");
			return "signup";
		} else {

			//Creates a user entry with the passed in user object. Via inheritence.
			driverService.save(new Driver(user,"Address Line 1","5 Stars","Courier"));
			
			model.addAttribute("message", "Saved user details");
			return "redirect:login.html";
		}
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {			
		UserLogin userLogin = new UserLogin();		
		model.addAttribute("userLogin", userLogin);
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		} else {
			boolean found = userService.findByLogin(userLogin.getUserName(), userLogin.getPassword());
			if (found) {				
				return "success";
			} else {				
				return "failure";
			}
		}
		
	}

}
