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
@Table(name="daily_activity")
public class DailyActivity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "daily_activity_id")
	@NotNull
	private int dailyActivityId;
	
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
	
	public DailyActivity() {
		
	}

	public DailyActivity(@NotNull String activity, String comments, BigDecimal cost, @NotNull int kilometers,
			@NotNull LocalDateTime dt_rec, @NotNull Vehicle vehicle) {
		super();
		this.activity = activity;
		this.comments = comments;
		this.cost = cost;
		this.kilometers = kilometers;
		this.dt_rec = dt_rec;
		this.vehicle = vehicle;
	}

	public int getDailyActivityId() {
		return dailyActivityId;
	}

	public void setDailyActivityId(int dailyActivityId) {
		this.dailyActivityId = dailyActivityId;
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

	@Override
	public String toString() {
		return "DailyActivity [dailyActivityId=" + dailyActivityId + ", activity=" + activity + ", comments=" + comments
				+ ", cost=" + cost + ", kilometers=" + kilometers + ", dt_rec=" + dt_rec + ", vehicle=" + vehicle + "]";
	}
	
}
