package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.service.DashboardService;
import com.appbindings.DashboardBinding;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping(value = "/dashboard")
	public String buildDashboardPage(Model model) {

		DashboardBinding dashboardQuote = dashboardService.generateQuote();

		model.addAttribute("dashboardBinding", dashboardQuote);

		return "dashboard";

	}
	
	@GetMapping(value="/logout")
	public String logout(Model mode , HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		return "redirect:/";
		
		
	}
}
