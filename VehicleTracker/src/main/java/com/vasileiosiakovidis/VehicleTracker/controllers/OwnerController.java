package com.vasileiosiakovidis.VehicleTracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;
import com.vasileiosiakovidis.VehicleTracker.entities.Owner;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.OwnerRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/owners")
public class OwnerController {
	
	
	private final OwnerRepository ownerRepository;
	private final MechanicRepository mechanicRepository;
	
	@Autowired
	public OwnerController(OwnerRepository ownerRepository,
							MechanicRepository mechanicRepository) {
		this.ownerRepository = ownerRepository;
		this.mechanicRepository = mechanicRepository;
	}
	
	/*
	 * 1. Find all Owners
	 * 2. Add owners as an attribute model
	 * 3. Return the View: owners/list-owners
	 */
	@GetMapping("")
	public String listOwners(Model model) {
		List<Owner> owners = ownerRepository.findAll();
		model.addAttribute("owners", owners);
		return "owners/list-owners";
	}
	
	/*
	 * 1. @Valid check the validation rules defined in the Owner class
	 * 2. Save the owner
	 * 3. redirect to the main root, sending a Get request to ("/"). 
	 */
	@PostMapping("add")
	public String addOwner(@Valid @ModelAttribute("owner") Owner owner) {
		ownerRepository.save(owner);
		return "redirect:/";
	}
	
	/*
	 * 1. Find the owner using the ownerId
	 * 2. Find all Mechanics
	 * 3. Add owner and Mechanics to the model
	 * 4. Return the View: owners/owner-details
	 */
	@GetMapping("/{ownerId}")
	public String showOwnerDetails(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = ownerRepository.findById(ownerId).orElse(null);
		List<Mechanic> mechanics = mechanicRepository.findAll();
		model.addAttribute("owner",owner);
		model.addAttribute("mechanics",mechanics);		
		return "owners/owner-details";
	}
}
