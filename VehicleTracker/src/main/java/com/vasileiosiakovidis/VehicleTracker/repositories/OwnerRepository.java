package com.vasileiosiakovidis.VehicleTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasileiosiakovidis.VehicleTracker.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
