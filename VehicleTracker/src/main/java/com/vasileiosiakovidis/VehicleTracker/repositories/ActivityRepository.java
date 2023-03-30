package com.vasileiosiakovidis.VehicleTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasileiosiakovidis.VehicleTracker.entities.DailyActivity;

public interface ActivityRepository extends JpaRepository<DailyActivity, Integer> {
	
}
