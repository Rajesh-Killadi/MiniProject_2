package com.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.entites.UsersMaster;
import com.app.service.DashboardService;
import com.app.service.UserService;
import com.appbindings.DashboardBinding;
import com.appbindings.LoginFormBinding;
import com.appbindings.RegisterFormBinding;
import com.appbindings.UpdatePasswordBinding;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DashboardService dashboardService;

	@GetMapping(value = "/")
	public String loginPage(Model model) {

		model.addAttribute("loginBinding", new LoginFormBinding());

		return "login";

	}

	@GetMapping(value = "/register")
	public String registerPage(Model model) {

		model.addAttribute("registerBinding", new RegisterFormBinding());

		return "register";

	}

	@GetMapping(value = "/updatePassword")
	public String pwdUpdatePage(Model model) {

		model.addAttribute("updatePasswordBinding", new UpdatePasswordBinding());

		return "updatePassword";
	}

	

	@PostMapping(value = "/register")
	public String Register(Model model, @ModelAttribute("registerBinding")RegisterFormBinding form) {

		boolean checkUser = userService.checkUser(form.getEmail());
		if (checkUser == false) {
			model.addAttribute("registerResponse", "Email is Already Registred");
		}

		boolean register = userService.register(form);

		if (register) {
			model.addAttribute("registerResponseSuccess", "Check your Email for Password");
		} else {
			model.addAttribute("registerResponse", "Registration Failed");
		}

		return "register";

	}

	@PostMapping(value = "/login")
	public String login(@ModelAttribute("loginBinding")LoginFormBinding form,Model model , HttpServletRequest req) {

			UsersMaster login = userService.login(form);
			if (login != null) {

				HttpSession session = req.getSession(true);
				session.setAttribute("userId", login.getUserId());

				if (login.getPasswordUpdated().equals("no")) {

					return "redirect:updatePassword";

				} else {
					return "redirect:dashboard";
				}
			} else {
				model.addAttribute("loginResponse","Invalid Credentials");
			}

		

	
		return "login";

	}

	@PostMapping(value = "/updatePassword")
	public String updatePassword(UpdatePasswordBinding form, Model model, HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		form.setUserId((Integer) session.getAttribute("userId"));

		boolean updatePassword = userService.updatePassword(form);

		if (updatePassword) {

			return "redirect:dashboard";
		}
		model.addAttribute("updatePwdResponse", "Passwords does not match");
		return "updatePassword";
	}

	@PostMapping(value="getCountries")
	@ResponseBody
	public Map<Integer, String> getCountries() {

		return userService.getCountries();
	}
	
	@PostMapping(value="getStates")
	@ResponseBody
	public Map<Integer, String> getStates(Integer countryId) {

		return userService.getStates(countryId);
	}
	
	@PostMapping(value="getCities")
	@ResponseBody
	public Map<Integer, String> getCities(Integer stateId) {

		return userService.getCities(stateId);
	}

}
