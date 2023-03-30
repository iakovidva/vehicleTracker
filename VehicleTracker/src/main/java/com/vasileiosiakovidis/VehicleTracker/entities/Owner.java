package com.vasileiosiakovidis.VehicleTracker.entities;

import java.util.ArrayList;
import java.util.List;

import com.vasileiosiakovidis.VehicleTracker.models.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="owner")
public class Owner extends Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "owner_id")
	@NotNull(message="required field")
	private int ownerId;
	
	@OneToMany(mappedBy = "owner",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<Vehicle> vehicles;

	@OneToMany(mappedBy = "owner",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<MechanicReview> mechanicReviews;
	
	public Owner() {
		
	}
	
	public Owner(@NotNull(message = "required field") String firstName,
			@NotNull(message = "required field") String lastName, @NotNull(message = "required field") String email,
			@NotNull(message = "required field") String phoneNumber, @NotNull(message = "required field") String password) {
		super(firstName, lastName, email, phoneNumber, password);
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public List<MechanicReview> getMechanicReviews() {
		return mechanicReviews;
	}

	public void setMechanicReviews(List<MechanicReview> mechanicReviews) {
		this.mechanicReviews = mechanicReviews;
	}

	public void addVehicle(Vehicle vehicle) {
		if (vehicles == null) {
			vehicles = new ArrayList<>();
		}
		vehicles.add(vehicle);
		vehicle.setOwner(this);
	}
	
	public void addReview(MechanicReview mechanicReview) {
		if (mechanicReviews == null) {
			mechanicReviews = new ArrayList<>();
		}
		mechanicReviews.add(mechanicReview);
		mechanicReview.setOwner(this);
	}

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", vehicles=" + vehicles + ", mechanicReviews=" + mechanicReviews
				+ ", toString()=" + super.toString() + "]";
	}

}
