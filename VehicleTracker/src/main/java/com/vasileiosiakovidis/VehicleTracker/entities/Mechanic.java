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
@Table(name="mechanic")
public class Mechanic extends Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "mechanic_id")
	@NotNull(message="required field")
	private int mechanicId;
	
	@OneToMany(mappedBy = "mechanic",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "mechanic",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<MechanicReview> mechanicReviews;
	
	@OneToMany(mappedBy = "mechanic",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<ServiceBook> serviceBooks;
	
	@OneToMany(mappedBy = "mechanic",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<MechanicSchedule> mechanicSchedules;
	
	@OneToMany(mappedBy = "mechanic",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<MechanicLocation> mechanicLocations;
	
	@Column(name = "workshop_name")
	private String workshopName;
	
	@Column(name = "specialisation")
	private String specialisation;
	
	
	public Mechanic() {
		
	}

	public Mechanic(@NotNull(message = "required field") String firstName,
			@NotNull(message = "required field") String lastName, @NotNull(message = "required field") String email,
			@NotNull(message = "required field") String phoneNumber, @NotNull(message = "required field") String password) {
		super(firstName, lastName, email, phoneNumber, password);	}

	public int getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(int mechanicId) {
		this.mechanicId = mechanicId;
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

	public String getWorkshopName() {
		return workshopName;
	}

	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public List<ServiceBook> getServiceBooks() {
		return serviceBooks;
	}

	public void setServiceBooks(List<ServiceBook> serviceBooks) {
		this.serviceBooks = serviceBooks;
	}

	public List<MechanicSchedule> getMechanicSchedules() {
		return mechanicSchedules;
	}

	public void setMechanicSchedules(List<MechanicSchedule> mechanicSchedules) {
		this.mechanicSchedules = mechanicSchedules;
	}

	public List<MechanicLocation> getMechanicLocations() {
		return mechanicLocations;
	}

	public void setMechanicLocations(List<MechanicLocation> mechanicLocations) {
		this.mechanicLocations = mechanicLocations;
	}

	public void addVehicle(Vehicle vehicle) {
		if (vehicles == null) {
			vehicles = new ArrayList<>();
		}
		vehicles.add(vehicle);
		vehicle.setMechanic(this);
	}
	
	public void addReview(MechanicReview mechanicReview) {
		if (mechanicReviews == null) {
			mechanicReviews = new ArrayList<>();
		}
		mechanicReviews.add(mechanicReview);
		mechanicReview.setMechanic(this);
	}
	
	public void addServiceBook(ServiceBook serviceBook) {
		if (serviceBooks == null) {
			serviceBooks = new ArrayList<>();
		}
		serviceBooks.add(serviceBook);
		serviceBook.setMechanic(this);
	}
	
	public void addMechanicSchedule(MechanicSchedule mechanicSchedule) {
		if (mechanicSchedules == null) {
			mechanicSchedules = new ArrayList<>();
		}
		mechanicSchedules.add(mechanicSchedule);
		mechanicSchedule.setMechanic(this);
	}

	public void addMechanicLocation(MechanicLocation mechanicLocation) {
		if (mechanicLocations == null) {
			mechanicLocations = new ArrayList<>();
		}
		mechanicLocations.add(mechanicLocation);
		mechanicLocation.setMechanic(this);
	}

	@Override
	public String toString() {
		return "Mechanic [mechanicId=" + mechanicId + ", vehicles=" + vehicles + ", mechanicReviews=" + mechanicReviews
				+ ", serviceBooks=" + serviceBooks + ", mechanicSchedules=" + mechanicSchedules + ", mechanicLocations="
				+ mechanicLocations + ", workshopName=" + workshopName + ", specialisation=" + specialisation
				+ ", toString()=" + super.toString() + "]";
	}	
}
