package com.vasileiosiakovidis.VehicleTracker.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;
import com.vasileiosiakovidis.VehicleTracker.entities.ServiceBook;
import com.vasileiosiakovidis.VehicleTracker.entities.Vehicle;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.ServiceBookRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.VehicleRepository;

@Controller
@RequestMapping("/api/v1/vehicles/servicebook")
public class ServiceBookController {

	private final ServiceBookRepository serviceBookRepository;
	private final VehicleRepository vehicleRepository;
	private final MechanicRepository mechanicRepository;
	
	@Autowired
	public ServiceBookController(ServiceBookRepository serviceBookRepository,
								VehicleRepository vehicleRepository,
								MechanicRepository mechanicRepository) {
		this.serviceBookRepository = serviceBookRepository;
		this.vehicleRepository = vehicleRepository;
		this.mechanicRepository = mechanicRepository;
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAddService(@RequestParam("vehicleId") int vehicleId, Model model) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		ServiceBook serviceBook = new ServiceBook();
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("serviceBook",serviceBook);
		return "serviceBook/serviceBook-form";
	}

	@PostMapping("/save")
	public String saveService(@ModelAttribute("serviceBook") ServiceBook serviceBook,
							@RequestParam("vehicleId") int vehicleId,
							@RequestParam("mechanicId") int mechanicId,
							RedirectAttributes redirectAttributes) {
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
		serviceBook.setMechanic(mechanic);
		serviceBook.setDt_rec(LocalDateTime.now());
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		vehicle.addServiceBook(serviceBook);
		serviceBook.setVehicle(vehicle);
		serviceBookRepository.save(serviceBook);
		redirectAttributes.addFlashAttribute("fromMechanicPage", true);
		return "redirect:/api/v1/owners/"+vehicle.getOwner().getOwnerId()+"/vehicles/"+vehicleId;
	}

	@GetMapping("/deleteServiceBook")
	public String deleteServiceBook(@RequestParam("vehicleId") int vehicleId,
								@RequestParam("serviceBookId") int serviceBookId,
								RedirectAttributes redirectAttributes) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		serviceBookRepository.deleteById(serviceBookId);
		redirectAttributes.addFlashAttribute("fromMechanicPage", true);
		return "redirect:/api/v1/owners/"+vehicle.getOwner().getOwnerId()+"/vehicles/"+vehicleId;
	}
}
