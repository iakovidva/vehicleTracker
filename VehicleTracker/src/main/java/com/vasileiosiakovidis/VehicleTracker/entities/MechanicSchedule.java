package com.vasileiosiakovidis.VehicleTracker.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="mechanic_schedule")
public class MechanicSchedule {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	@NotNull
	private Mechanic mechanic;
	
	@Id
	@Column(name = "appointment_date")
	@NotNull
	private LocalDate appointmentDate;

	@Column(name = "is_available")
	private Boolean isAvailable;
	
	@Id
	@Column(name = "start_time")
	@NotNull
	private LocalTime startTime;
	
	@Id
	@Column(name = "end_time")
	@NotNull
	private LocalTime endTime;
	
	public MechanicSchedule() {
		
	}

	public MechanicSchedule(@NotNull Mechanic mechanic, @NotNull LocalDate appointmentDate,
			Boolean isAvailable, @NotNull LocalTime startTime, @NotNull LocalTime endTime) {
		super();
		this.mechanic = mechanic;
		this.appointmentDate = appointmentDate;
		this.isAvailable = isAvailable;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "MechanicSchedule [mechanic=" + mechanic + ", appointmentDate=" + appointmentDate + ", isAvailable="
				+ isAvailable + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
