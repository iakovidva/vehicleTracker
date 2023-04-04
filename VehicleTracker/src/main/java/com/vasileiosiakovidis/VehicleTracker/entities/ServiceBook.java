package com.vasileiosiakovidis.VehicleTracker.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="service_book")
public class ServiceBook {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "service_book_id")
	@NotNull
	private int serviceBookId;

	@Column(name = "activity")
	@NotNull
	private String activity;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "cost")
	private BigDecimal cost;
	
	@Column(name = "kilometers")
	@NotNull
	private int kilometers;
	
	@Column(name = "dt_rec")
	@NotNull
	private LocalDateTime dt_rec;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	@NotNull
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	@NotNull
	private Mechanic mechanic;
	

	public ServiceBook() {
		
	}

	public ServiceBook(@NotNull String activity, String comments, BigDecimal cost, @NotNull int kilometers,
			@NotNull LocalDateTime dt_rec, @NotNull Vehicle vehicle, @NotNull Mechanic mechanic) {
		super();
		this.activity = activity;
		this.comments = comments;
		this.cost = cost;
		this.kilometers = kilometers;
		this.dt_rec = dt_rec;
		this.vehicle = vehicle;
		this.mechanic = mechanic;
	}

	public int getServiceBookId() {
		return serviceBookId;
	}

	public void setServiceBookId(int serviceBookId) {
		this.serviceBookId = serviceBookId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public int getKilometers() {
		return kilometers;
	}

	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
	}

	public LocalDateTime getDt_rec() {
		return dt_rec;
	}

	public void setDt_rec(LocalDateTime dt_rec) {
		this.dt_rec = dt_rec;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	@Override
	public String toString() {
		return "ServiceBook [serviceBookId=" + serviceBookId + ", activity=" + activity + ", comments=" + comments
				+ ", cost=" + cost + ", kilometers=" + kilometers + ", dt_rec=" + dt_rec + ", vehicle=" + vehicle
				+ ", mechanic=" + mechanic + "]";
	}
	
}
