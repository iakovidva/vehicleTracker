package com.vasileiosiakovidis.VehicleTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasileiosiakovidis.VehicleTracker.entities.MechanicLocation;

public interface MechanicLocationRepository extends JpaRepository<MechanicLocation, Integer> {
	
}
