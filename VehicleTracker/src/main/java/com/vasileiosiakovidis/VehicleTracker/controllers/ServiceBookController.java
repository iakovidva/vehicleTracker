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
	
	/*
	 * 1. Find the vehicle to add an serviceBook item by vehicleId
	 * 2. Create new ServiceBook object 
	 * 3. Add vehicle and serviceBook to the model
	 * 4. Return the View serviceBook/serviceBook-form
	 */
	@GetMapping("/showFormForAdd")
	public String showFormForAddService(@RequestParam("vehicleId") int vehicleId, Model model) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		ServiceBook serviceBook = new ServiceBook();
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("serviceBook",serviceBook);
		return "serviceBook/serviceBook-form";
	}
	
	/*
	 * 1. Find the mechanic to save the serviceBook by mechanicId
	 * 2. Add mechanic to the serviceBook object
	 * 3. Find the vehicle by vehicleId
	 * 4. Add the serviceBook to the vehicle object
	 * 5. Associate the vehicle with the serviceBook object
	 * 6. Save the serviceBok object.
	 * 5. Add redirect attribute "fromMechanicPage"=false
	 * 6. Send GET request to the "redirect:/api/v1/owners/"+vehicle.getOwner().getOwnerId()+"/vehicles/"+vehicleId
	 */
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
	
	/*
	 * 1. Accept the vehicleId, serviceBookId by the vehicle-details.html
	 * 2. Find the vehicle to build the redirect link
	 * 3. Delete the serviceBookId
	 * 4. Add redirect attribute "fromMechanicPage"=true
	 * 5. Send GET request to the "redirect:/api/v1/owners/"+vehicle.getOwner().getOwnerId()+"/vehicles/"+vehicleId
	 */
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
