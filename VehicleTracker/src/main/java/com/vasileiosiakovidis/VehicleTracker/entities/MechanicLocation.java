package com.vasileiosiakovidis.VehicleTracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="mechanic_location")
@IdClass(MechanicLocation.class)
public class MechanicLocation {
	
	@Id
	@NotNull
	@Column(name="mechanic_location_id")
	private int mechanicLocationId;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	@NotNull
	private Mechanic mechanic;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "postal_code")
	private int postalCode;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "street_number")
	private int streetNumber;
	
	@Column(name = "coordinates")
	private String coordinates;
		
	public MechanicLocation() {
		
	}

	public MechanicLocation(@NotNull Mechanic mechanic, String city, int postalCode, String streetName,
			int streetNumber, String coordinates) {
		super();
		this.mechanic = mechanic;
		this.city = city;
		this.postalCode = postalCode;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.coordinates = coordinates;
	}
	
	public int getMechanicLocationId() {
		return mechanicLocationId;
	}

	public void setMechanicLocationId(int mechanicLocationId) {
		this.mechanicLocationId = mechanicLocationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	@Override
	public String toString() {
		return "MechanicLocation [mechanicLocationId=" + mechanicLocationId + ", mechanic=" + mechanic + ", city="
				+ city + ", postalCode=" + postalCode + ", streetName=" + streetName + ", streetNumber=" + streetNumber
				+ ", coordinates=" + coordinates + "]";
	}
	
}
