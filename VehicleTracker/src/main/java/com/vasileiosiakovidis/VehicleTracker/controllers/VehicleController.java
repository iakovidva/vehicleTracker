package com.vasileiosiakovidis.VehicleTracker.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;
import com.vasileiosiakovidis.VehicleTracker.entities.Owner;
import com.vasileiosiakovidis.VehicleTracker.entities.Vehicle;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.OwnerRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.VehicleRepository;

@Controller
public class VehicleController {

	
	private final VehicleRepository vehicleRepository;
	private final OwnerRepository ownerRepository;
	private final MechanicRepository mechanicRepository;
	
	@Autowired
	public VehicleController(VehicleRepository vehicleRepository, 
								OwnerRepository ownerRepository,
								MechanicRepository mechanicRepository) {
		this.vehicleRepository = vehicleRepository;
		this.ownerRepository = ownerRepository;
		this.mechanicRepository = mechanicRepository;
	}
	
	@GetMapping("/api/v1/owners/{ownerId}/vehicles/add")
	public String formForAddVehicle(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = ownerRepository.findById(ownerId).orElse(null);
		Vehicle vehicle = new Vehicle();
		List<Mechanic> mechanics = mechanicRepository.findAll();
		model.addAttribute("owner",owner);
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("mechanics",mechanics);
		
		return "vehicles/vehicle-form";
	}

	@GetMapping("/api/v1/owners/{ownerId}/vehicles/{vehicleId}/update")
	public String formForUpdateVehicle(@PathVariable("ownerId") int ownerId, 
										@PathVariable("vehicleId") int vehicleId,
										Model model) {
		Owner owner = ownerRepository.findById(ownerId).orElse(null);
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		List<Mechanic> mechanics = mechanicRepository.findAll();
		model.addAttribute("owner",owner);
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("mechanics",mechanics);
		return "vehicles/vehicle-form-update";
	}
	
	@PostMapping("/api/v1/owners/{ownerId}/vehicles/save")
	public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle,
								@PathVariable("ownerId") int ownerId) {
		Owner owner = ownerRepository.findById(ownerId).orElse(null);
		vehicle.setDtEff(LocalDateTime.now());
		vehicle.setDtEnd(LocalDateTime.of(2099, 12, 31,0,0));
		owner.addVehicle(vehicle);
		vehicle.setOwner(owner);
		vehicleRepository.save(vehicle);
		return "redirect:/api/v1/owners/"+ownerId;
	}

	@PostMapping("/api/v1/owners/{ownerId}/vehicles/update")
	public String updateVehicle(@ModelAttribute("vehicle") Vehicle vehicle,
								@PathVariable("ownerId") int ownerId) {
		Owner owner = ownerRepository.findById(ownerId).orElse(null);
		vehicle.setDtEff(LocalDateTime.now());
		vehicle.setDtEnd(LocalDateTime.of(2099, 12, 31,0,0));
		vehicle.setOwner(owner);
		vehicleRepository.save(vehicle);
		return "redirect:/api/v1/owners/"+ownerId;
	}
	
	@GetMapping("/api/v1/owners/{ownerId}/vehicles/{vehicleId}/delete")
	public String deleteVehicle(@PathVariable("vehicleId") int vehicleId,
								@PathVariable("ownerId") int ownerId) {
		vehicleRepository.deleteById(vehicleId);
		return "redirect:/api/v1/owners/"+ownerId;
	}

	
	@PostMapping("/api/v1/owners/{ownerId}/vehicles/{vehicleId}/saveMechanic")
	public String saveMechanic(@PathVariable("vehicleId") int vehicleId, 
								@PathVariable("ownerId") int ownerId, 
								@RequestParam("selectedMechanic") int mechanicId) {
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);

		mechanic.addVehicle(vehicle);
		vehicle.setMechanic(mechanic);

		vehicleRepository.save(vehicle);
		return "redirect:/api/v1/owners/"+ownerId;
	}
	
	@PostMapping("/api/v1/owners/{ownerId}/vehicles/{vehicleId}")
	public String showVehicleDetails(@PathVariable("ownerId") int ownerId,
									@PathVariable("vehicleId") int vehicleId,
									@RequestParam(required = false) boolean fromMechanicPage,
									Model model) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("fromMechanicPage", fromMechanicPage);
		return "vehicles/vehicle-details";
	}
	
	@GetMapping("/api/v1/owners/{ownerId}/vehicles/{vehicleId}")
	public String showVehicleDetails2(@PathVariable("ownerId") int ownerId,
									@PathVariable("vehicleId") int vehicleId,
									@RequestParam(required = false) boolean fromMechanicPage,
									Model model) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		model.addAttribute("vehicle",vehicle);
	    model.addAttribute("fromMechanicPage", model.asMap().get("fromMechanicPage"));
		return "vehicles/vehicle-details";
	}
}
