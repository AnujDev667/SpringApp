package com.springboot.springapp.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.springapp.exception.InvalidCredentialsException;
import com.springboot.springapp.model.User;
import com.springboot.springapp.service.MyService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
//	static {
//		System.out.println("controller loaded");
//	}
	@Autowired
	private MyService myService; //DI
@GetMapping("/")  //@RequestMapping
//public String showLogin() {
//	return "login";
public ModelAndView showLogin(Model model,HttpServletRequest httpServletRequest,ModelAndView mav) {
	model.addAttribute("username","sansu@bogus.com");
	List<String> listSports = Arrays.asList("Ludo","Sudoku","Cricket");
	httpServletRequest.setAttribute("list", listSports);
	mav.setViewName("jsp_demo");
	mav.addObject("current_date",LocalDate.now());
	return mav;
}

@GetMapping("/login-form")
public String readLogin(HttpServletRequest req) {
	String username = req.getParameter("username");
	String password = req.getParameter("password");
//	System.out.println(username+ "==========" + password);
//	return "dashboard";
	try {
		User user = myService.isValid(username,password);
		return "dashboard";
	}catch(InvalidCredentialsException e) {
		req.setAttribute("msg", e.getMessage());
		return "login";
	}
}
}
