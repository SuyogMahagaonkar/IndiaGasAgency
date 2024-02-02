package com.psl.IGA.demo.project.controller;

import java.security.Principal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psl.IGA.demo.project.dto.UserDto;
import com.psl.IGA.demo.project.model.Booking;
import com.psl.IGA.demo.project.model.User;
import com.psl.IGA.demo.project.repositories.UserRepository;
import com.psl.IGA.demo.project.service.BookingService;
import com.psl.IGA.demo.project.service.CustomUserDetail;
import com.psl.IGA.demo.project.service.UserService;



@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserRepository userRepository;
	

	@GetMapping("/registration")
	public String getRegistrationPage() {
		return "register";
	}
	@GetMapping("/forgotpass")
	public String getForgotPassword() {
		return "forgotpassword";
	}
	@GetMapping("/forgotpass/getverified/otp")
	public String getVerifiedByOTP(@ModelAttribute("user") UserDto userDto, Model model) {
		
		return "otp";
	}
//	@PostMapping("/forgotpass/getverified/otp")
//	public String getVerifiedByOTP1() {
//		return "otp";
//	}
	@PostMapping("/forgotpass/getverified/otp")
	public String getForgotPassword1(@RequestParam("email") String email,@ModelAttribute("user") UserDto userDto, Model model) {
		int otp=userService.otpGenerate(email);
		model.addAttribute("message", "OTP sent on "+ email);
		model.addAttribute("otp", otp);
		model.addAttribute("user",userDto);
		return "forgotpassword";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registerd Successfuly");
		return "register";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("user-page")
	public String userPage(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		User user = userRepository.findByEmail(userDetails.getUsername());
		
		Date previousBookingDate=null;

		if (user.getBooking()!=null) {
			Booking booking_DB=user.getBooking();
			previousBookingDate=booking_DB.getBooking_date();
		}
		

		System.out.println("check 1 pass: "+ previousBookingDate);
		
		model.addAttribute("preBookingDate", previousBookingDate);
		return "user";
	}

	
	//****************************me
	@PostMapping("user-page")
	public String bookCylinder(@ModelAttribute("booking") Booking booking, Model model,Principal principal) {
		
		bookingService.save(booking,principal.getName());//added principal and principal.getName
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName()); //me
		
		model.addAttribute("user",userDetails);	//me
		model.addAttribute("message", "Booked Successfuly");
		return "user";
	}
	
	
	
	//**********************************
	@GetMapping("admin-page")
	public String adminPage(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "admin";
	}
	
	@GetMapping("/profile")
	public String getProfile(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "profile";
	}
}

