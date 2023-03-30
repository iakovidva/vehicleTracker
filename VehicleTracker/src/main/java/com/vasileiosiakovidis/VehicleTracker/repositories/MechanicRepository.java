package com.vasileiosiakovidis.VehicleTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;

public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {

}
