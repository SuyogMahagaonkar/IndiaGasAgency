package com.psl.IGA.demo.project.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(EntityListeners.class)

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	
	
	private String name;
	private String mobile;
	private String email;
	private String password;
	private String role;
	private String address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	public User() {
		super();
	}
	public User(String name, String mobile, String email, String password, String role, String address,Booking booking) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
		this.booking = booking;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	 
}
