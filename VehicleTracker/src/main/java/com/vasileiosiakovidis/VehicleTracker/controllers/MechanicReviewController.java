package com.vasileiosiakovidis.VehicleTracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;
import com.vasileiosiakovidis.VehicleTracker.entities.MechanicReview;
import com.vasileiosiakovidis.VehicleTracker.entities.Owner;
import com.vasileiosiakovidis.VehicleTracker.entities.ServiceBook;
import com.vasileiosiakovidis.VehicleTracker.entities.Vehicle;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicReviewRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.OwnerRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.ServiceBookRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.VehicleRepository;

import jakarta.persistence.EntityManager;

@Controller
public class MechanicReviewController {

	
	private final MechanicReviewRepository mechanicReviewRepository;
	private final ServiceBookRepository serviceBookRepository;
	private final VehicleRepository vehicleRepository;
	private final MechanicRepository mechanicRepository;
	private final OwnerRepository ownerRepository;


	@Autowired
	public MechanicReviewController(MechanicReviewRepository mechanicReviewRepository,
									ServiceBookRepository serviceBookRepository,
									VehicleRepository vehicleRepository,
									MechanicRepository mechanicRepository,
									OwnerRepository ownerRepository) {
		 this.mechanicReviewRepository = mechanicReviewRepository;
		 this.serviceBookRepository = serviceBookRepository;
		 this.vehicleRepository = vehicleRepository;
		 this.mechanicRepository = mechanicRepository;
		 this.ownerRepository = ownerRepository;
	}
	
	@GetMapping("/rating")
	public String showRatingForm(@RequestParam("serviceBookId") int serviceBookId,
			@RequestParam("ownerId") int ownerId,
//								@RequestParam("vehicleId") int vehicleId,
								Model model) {
		ServiceBook serviceBook = serviceBookRepository.findById(serviceBookId).orElse(null); // mesw serviceBook vriskw vehicleId, mechanicId, 
		MechanicReview review = new MechanicReview();
		Owner owner = ownerRepository.findById(ownerId).orElse(null);
		review.setOwner(owner);
		model.addAttribute("serviceBook",serviceBook);
		model.addAttribute("review",review);
		return "mechanicReviews/rating-form";
	}
	
	@PostMapping("/saveReview")
	public String saveReview(@ModelAttribute("review") MechanicReview review,
							@RequestParam("vehicleId") int vehicleId,
							@RequestParam("mechanicId") int mechanicId
							) {
		System.out.println("save clicked!");
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		review.setVehicle(vehicle);
		
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
		review.setMechanic(mechanic);
		
//		review.setOwner(owner);
		
		mechanicReviewRepository.save(review);
		return "redirect:/api/v1/mechanics";
	}
	
	
//	@PostMapping("/save")
//	public String saveActivity(@ModelAttribute("dailyActivity") DailyActivity activity,
//								@RequestParam("vehicleId") int vehicleId,
//								RedirectAttributes redirectAttributes) {
//		activity.setDt_rec(LocalDateTime.now());
//		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
//		vehicle.addDailyActivity(activity);
//		activity.setVehicle(vehicle);
//		
//		activityRepository.save(activity);
//		
//		redirectAttributes.addFlashAttribute("fromMechanicPage", false);
//		return "redirect:/api/v1/owners/"+vehicle.getOwner().getOwnerId()+"/vehicles/"+vehicleId;
//	}
}
