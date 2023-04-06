package com.vasileiosiakovidis.VehicleTracker.controllers;

import java.math.BigDecimal;
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
import com.vasileiosiakovidis.VehicleTracker.entities.MechanicReview;
import com.vasileiosiakovidis.VehicleTracker.entities.Vehicle;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.MechanicReviewRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.VehicleRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/mechanics")
public class MechanicController {
	
	
	private final MechanicRepository mechanicRepository;
	private final MechanicReviewRepository mechanicReviewRepository;
	private final VehicleRepository vehicleRepository;
	@Autowired
	public MechanicController(MechanicRepository mechanicRepository,
							MechanicReviewRepository mechanicReviewRepository,
							VehicleRepository vehicleRepository) {
		this.mechanicRepository = mechanicRepository;
		this.mechanicReviewRepository = mechanicReviewRepository;
		this.vehicleRepository = vehicleRepository;
	}
	
	@GetMapping("")
	public String listMechanics(Model model) {
		List<Mechanic> mechanics = mechanicRepository.findAll();
		model.addAttribute("mechanics", mechanics);
		return "mechanics/list-mechanics";
	}
	
	@PostMapping("add")
	public String addMechanic(@Valid @ModelAttribute("mechanic") Mechanic mechanic) {
		mechanicRepository.save(mechanic);
		return "redirect:/";
	}
	
	@GetMapping("/{mechanicId}/delete")
	public String deleteMechanic(@PathVariable("mechanicId") int mechanicId) {
		mechanicRepository.deleteById(mechanicId);
		return "redirect:/api/v1/mechanics";
	}
	
	
	@GetMapping("/{mechanicId}")
	public String showMechanicDetails(@PathVariable("mechanicId") int mechanicId,
									@RequestParam(name = "reviewsPage", defaultValue = "0") int reviewsPage,
						            @RequestParam(name = "vehiclesPage", defaultValue = "0") int vehiclesPage,
						            Model model) {
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
		model.addAttribute("mechanic",mechanic);
		
		Double averageRating = mechanic.getMechanicReviews().stream()
			.map(review -> review.getRating())
			.mapToDouble(BigDecimal::doubleValue)
			.average()
			.orElse(0);
		
		model.addAttribute("averageRating", averageRating);

		Pageable reviewsPageable = PageRequest.of(reviewsPage, 5, Sort.by("dtRec").descending());
        Pageable vehiclesPageable = PageRequest.of(vehiclesPage, 10, Sort.by("dtEff").descending());

        Page<MechanicReview> reviews = mechanicReviewRepository.findAllByMechanic(mechanic, reviewsPageable);
        Page<Vehicle> vehicles = vehicleRepository.findAllByMechanic(mechanic, vehiclesPageable);

        // Check if reviews or vehicles are null and create empty pages if needed
	    if (reviews == null) {
	        reviews = Page.empty(reviewsPageable);
	    }
	    if (vehicles == null) {
	        vehicles = Page.empty(vehiclesPageable);
	    }
	    
        model.addAttribute("reviewsWithPage", reviews);
        model.addAttribute("vehiclesWithPage", vehicles);

		return "mechanics/mechanic-details";
	}

	@GetMapping("/{mechanicId}/update")
	public String formForUpdateMechanic(@PathVariable("mechanicId") int mechanicId,Model model) {
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);
		model.addAttribute("mechanic",mechanic);
		return "mechanics/mechanic-form-update";
	}
	
	@PostMapping("/{mechanicId}/update")
	public String updateMechanic(@PathVariable("mechanicId") int mechanicId,
								@ModelAttribute("mechanic") Mechanic mechanic) {
		mechanicRepository.save(mechanic);
		return "redirect:/api/v1/mechanics/"+mechanicId;
	}
}
