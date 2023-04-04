package com.vasileiosiakovidis.VehicleTracker.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="mechanic_review")
public class MechanicReview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "review_id")
	@NotNull(message="required field")
	private int reviewId;
	
	@Column(name = "rating")
	@NotNull(message="required field")
	private BigDecimal rating;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "dt_rec")
	@NotNull(message="required field")
	private LocalDateTime dtRec;
	
	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	@NotNull
	private Mechanic mechanic;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@NotNull
	private Owner owner;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	@NotNull
	private Vehicle vehicle;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="service_book_id")
	@NotNull
	private ServiceBook serviceBook;
	
	public MechanicReview() {
		
	}

	public MechanicReview(@NotNull(message = "required field") BigDecimal rating, String comment,
			@NotNull(message = "required field") LocalDateTime dtRec, @NotNull Mechanic mechanic) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.dtRec = dtRec;
		this.mechanic = mechanic;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getDtRec() {
		return dtRec;
	}

	public void setDtRec(LocalDateTime dtRec) {
		this.dtRec = dtRec;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public ServiceBook getServiceBook() {
		return serviceBook;
	}

	public void setServiceBook(ServiceBook serviceBook) {
		this.serviceBook = serviceBook;
	}

	@Override
	public String toString() {
		return "MechanicReview [reviewId=" + reviewId + ", rating=" + rating + ", comment=" + comment + ", dtRec="
				+ dtRec + ", mechanic=" + mechanic + ", owner=" + owner + ", vehicle=" + vehicle + "]";
	}
		
}
