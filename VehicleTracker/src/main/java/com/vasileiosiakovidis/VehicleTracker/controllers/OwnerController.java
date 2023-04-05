package com.vasileiosiakovidis.VehicleTracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("")
	public String listOwners(@RequestParam(name = "ownersPage", defaultValue = "0") int ownersPage, Model model) {
		Pageable ownersPageable = PageRequest.of(ownersPage, 5, Sort.by("lastName").ascending());
		Page<Owner> ownersWithPage = ownerRepository.findAll(ownersPageable);
		if (ownersWithPage == null) {
			ownersWithPage = Page.empty(ownersPageable);
	    }
		model.addAttribute("ownersWithPage", ownersWithPage);
		return "owners/list-owners";
	}
	
	@PostMapping("add")
	public String addOwner(@Valid @ModelAttribute("owner") Owner owner) {
		ownerRepository.save(owner);
		return "redirect:/";
	}
	
	@GetMapping("/{ownerId}")
	public String showOwnerDetails(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = ownerRepository.findById(ownerId).orElse(null);
		List<Mechanic> mechanics = mechanicRepository.findAll();
		model.addAttribute("owner",owner);
		model.addAttribute("mechanics",mechanics);		
		return "owners/owner-details";
	}
	
	@GetMapping("/{ownerId}/delete")
	public String deleteOwner(@PathVariable("ownerId") int ownerId) {
		ownerRepository.deleteById(ownerId);
		return "redirect:/api/v1/owners";
	}
}
