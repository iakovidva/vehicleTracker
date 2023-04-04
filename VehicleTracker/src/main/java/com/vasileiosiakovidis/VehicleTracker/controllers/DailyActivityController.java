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

import com.vasileiosiakovidis.VehicleTracker.entities.DailyActivity;
import com.vasileiosiakovidis.VehicleTracker.entities.Vehicle;
import com.vasileiosiakovidis.VehicleTracker.repositories.ActivityRepository;
import com.vasileiosiakovidis.VehicleTracker.repositories.VehicleRepository;

@Controller
@RequestMapping("/api/v1/vehicles/activities")
public class DailyActivityController {

	private final ActivityRepository activityRepository;
	private final VehicleRepository vehicleRepository;
	
	@Autowired
	public DailyActivityController(ActivityRepository activityRepository,
									VehicleRepository vehicleRepository) {
		this.activityRepository = activityRepository;
		this.vehicleRepository = vehicleRepository;
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAddActivity(@RequestParam("vehicleId") int vehicleId, Model model) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		DailyActivity activity = new DailyActivity();
		LocalDateTime defaultDtRec = LocalDateTime.now();
		model.addAttribute("defaultDtRec", defaultDtRec);
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("dailyActivity",activity);
		return "activities/activity-form";
	}
	
	@PostMapping("/save")
	public String saveActivity(@ModelAttribute("dailyActivity") DailyActivity activity,
								@RequestParam("vehicleId") int vehicleId,
								RedirectAttributes redirectAttributes) {
		activity.setDt_rec(LocalDateTime.now());
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		vehicle.addDailyActivity(activity);
		activity.setVehicle(vehicle);
		
		activityRepository.save(activity);
		
		redirectAttributes.addFlashAttribute("fromMechanicPage", false);
		return "redirect:/api/v1/owners/"+vehicle.getOwner().getOwnerId()+"/vehicles/"+vehicleId;
	}
	
	@GetMapping("/deleteActivity")
	public String deleteActivity(@RequestParam("vehicleId") int vehicleId,
								@RequestParam("activityId") int activityId,
								RedirectAttributes redirectAttributes) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
		activityRepository.deleteById(activityId);
		redirectAttributes.addFlashAttribute("fromMechanicPage", false);
		return "redirect:/api/v1/owners/"+vehicle.getOwner().getOwnerId()+"/vehicles/"+vehicleId;
	}
}
