package com.vasileiosiakovidis.VehicleTracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;
import com.vasileiosiakovidis.VehicleTracker.entities.Owner;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/login-form")
	public String showLoginForm() {
		return "login-form";
	}
	
	@GetMapping("/registration-form")
	public String showRegistrationForm(Model model) {
		Owner owner = new Owner();
		Mechanic mechanic = new Mechanic();
		model.addAttribute("owner",owner);
		model.addAttribute("mechanic",mechanic);
	
		return "registration-form";
	}
}
