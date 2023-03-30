package com.vasileiosiakovidis.VehicleTracker.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="vehicle")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	@NotNull
	private int vehicleId;

	@Column(name = "make")
	@NotNull
	private String make;

	@Column(name = "model")
	@NotNull
	private String model;
	
	@Column(name = "license_plate")
	@NotNull
	private String licensePlate;
	
	@Column(name = "production_date")
	private String productionDate;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@NotNull
	private Owner owner;
	
	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	private Mechanic mechanic;
	
	@Column(name="dt_eff")
	@NotNull
	private LocalDateTime dtEff;
	
	@Column(name="dt_end")
	@NotNull
	private LocalDateTime dtEnd;

	@OneToMany(mappedBy = "vehicle",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<MechanicReview> mechanicReviews;
	
	@OneToMany(mappedBy = "vehicle",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
	private List<DailyActivity> dailyActivities;
	
	@OneToMany(mappedBy = "vehicle",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private List<ServiceBook> serviceBooks;
	
	public Vehicle() {
		
	}
	
	public Vehicle(@NotNull String make, @NotNull String model, @NotNull String licensePlate,
			String productionDate, @NotNull Owner owner, @NotNull Mechanic mechanic, @NotNull LocalDateTime dtEff,
			@NotNull LocalDateTime dtEnd) {
		this.make = make;
		this.model = model;
		this.licensePlate = licensePlate;
		this.productionDate = productionDate;
		this.owner = owner;
		this.mechanic = mechanic;
		this.dtEff = dtEff;
		this.dtEnd = dtEnd;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licencePlate) {
		this.licensePlate = licencePlate;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public LocalDateTime getDtEff() {
		return dtEff;
	}

	public void setDtEff(LocalDateTime dtEff) {
		this.dtEff = dtEff;
	}

	public LocalDateTime getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(LocalDateTime dtEnd) {
		this.dtEnd = dtEnd;
	}

	public List<MechanicReview> getMechanicReviews() {
		return mechanicReviews;
	}

	public List<DailyActivity> getDailyActivities() {
		return dailyActivities;
	}

	public void setDailyActivities(List<DailyActivity> dailyActivities) {
		this.dailyActivities = dailyActivities;
	}

	public List<ServiceBook> getServiceBooks() {
		return serviceBooks;
	}

	public void setServiceBooks(List<ServiceBook> serviceBook) {
		this.serviceBooks = serviceBook;
	}

	public void setMechanicReviews(List<MechanicReview> mechanicReviews) {
		this.mechanicReviews = mechanicReviews;
	}

	public void addReview(MechanicReview mechanicReview) {
		if (mechanicReviews == null) {
			mechanicReviews = new ArrayList<>();
		}
		mechanicReviews.add(mechanicReview);
		mechanicReview.setVehicle(this);
	}
	
	public void addDailyActivity(DailyActivity dailyActivity) {
		if (dailyActivities == null) {
			dailyActivities = new ArrayList<>();
		}
		dailyActivities.add(dailyActivity);
		dailyActivity.setVehicle(this);
	}
	
	public void addServiceBook(ServiceBook serviceBook) {
		if (serviceBooks == null) {
			serviceBooks = new ArrayList<>();
		}
		serviceBooks.add(serviceBook);
		serviceBook.setVehicle(this);
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", make=" + make + ", model=" + model + ", licencePlate="
				+ licensePlate + ", productionDate=" + productionDate + ", owner=" + owner + ", mechanic=" + mechanic
				+ ", dtEff=" + dtEff + ", dtEnd=" + dtEnd + ", mechanicReviews=" + mechanicReviews + "]";
	}
	
}
