package com.psl.IGA.demo.project.model;

import java.sql.Date;
import java.util.*;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(EntityListeners.class)
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long book_id;
	
	
	private Date booking_date;
	private double price;
	private String category;
	private String status;
	public Long getBook_id() {
		return book_id;
	}
	public void setBook_id(Long book_id) {
		this.book_id = book_id;
	}
	public Date getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Booking(Date booking_date, double price, String category,String status) {
		super();
		this.booking_date = booking_date;
		this.price = price;
		this.category = category;
		this.status = status;
	}
	public Booking() {
		super();
	}
	
	
	
	
	
	
	

}
