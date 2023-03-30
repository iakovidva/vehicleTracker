package com.vasileiosiakovidis.VehicleTracker.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vasileiosiakovidis.VehicleTracker.entities.Mechanic;
import com.vasileiosiakovidis.VehicleTracker.entities.MechanicReview;

public interface MechanicReviewRepository extends JpaRepository<MechanicReview, Integer>{
	Page<MechanicReview> findAllByMechanic(Mechanic mechanic, Pageable pageable);

}
