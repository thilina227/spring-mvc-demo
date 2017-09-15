package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultRoute(ModelMap model, HttpServletRequest request) {
		model.addAttribute("message", "Login");

		if (request.getSession().getAttribute("loggedin") != null) {
			model.addAttribute("message", "Hello " + request.getSession().getAttribute("username"));
			return "welcome";
		}
		return "hello";
	}


	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request) {
		model.addAttribute("message", "Login");

		if (request.getSession().getAttribute("loggedin") != null) {
			model.addAttribute("message", "Hello " + request.getSession().getAttribute("username"));
			return "welcome";
		}
		return "hello";
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, ModelMap model,
						HttpServletRequest request) {

		if (username.equals("user") && password.equals("pass")){
			request.getSession().setAttribute("loggedin", "true");
			request.getSession().setAttribute("username", username);
			model.addAttribute("message", "Hello " + username);
			return "welcome";
		} else {
			model.addAttribute("message", "Wrong username or password");
			return "hello";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout( ModelMap model, HttpServletRequest request){
		request.getSession().removeAttribute("loggedin");
		request.getSession().removeAttribute("username");
		return "hello";
	}



}