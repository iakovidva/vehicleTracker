package com.vasileiosiakovidis.VehicleTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;
import com.vasileiosiakovidis.VehicleTracker.entities.MechanicLocation;
import com.vasileiosiakovidis.VehicleTracker.entities.Vehicle;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicLocationRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicRepository;

@Controller
public class MechanicLocationController {

	
	MechanicLocationRepository mechanicLocationRepository;
	MechanicRepository mechanicRepository;
	
	@Autowired
	public MechanicLocationController(MechanicLocationRepository mechanicLocationRepository,
										MechanicRepository mechanicRepository) {
		this.mechanicLocationRepository = mechanicLocationRepository;
		this.mechanicRepository = mechanicRepository;
	}
	
	@GetMapping("api/v1/mechanics/{mechanicId}/add-location")
	public String showFormForAdd(@PathVariable("mechanicId") int mechanicId, Model model) {
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
		MechanicLocation mechanicLocation = new MechanicLocation();
		model.addAttribute(mechanic);
		model.addAttribute(mechanicLocation);
		return "mechanics/mechanic-location-form";
	}
	
	@PostMapping("/saveLocation")
	public String saveLocation(@ModelAttribute("mechanicLocation") MechanicLocation location,
							@RequestParam("mechanicId") int mechanicId) {
		
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
		location.setMechanic(mechanic);
		mechanicLocationRepository.save(location);
		return "redirect:/api/v1/mechanics/"+mechanicId+"/update";
	}
}
