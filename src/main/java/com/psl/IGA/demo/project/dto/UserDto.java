package com.psl.IGA.demo.project.dto;

import com.psl.IGA.demo.project.model.Booking;

public class UserDto {

	private String name;
	private String mobile;
	private String email;
	private String password;
	private String role;
	private String address;
	private Booking booking; //added me
	
	public UserDto(String name, String mobile, String email, String password, String address, Booking booking) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.role=role;
		this.address = address;
		this.booking = booking;
		
	}
	
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
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
	
	
	
}
