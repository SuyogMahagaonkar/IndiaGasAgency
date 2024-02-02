package com.psl.IGA.demo.project.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.IGA.demo.project.model.Booking;
import com.psl.IGA.demo.project.model.User;
import com.psl.IGA.demo.project.repositories.BookingRepository;
import com.psl.IGA.demo.project.repositories.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@Service
public class BookingServiceImpl implements BookingService{
	
	
    Long book_id;
    
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Booking save(Booking booking, String username) {	//added string username
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		double price=doCalculation(booking.getBooking_date(),user);
		Booking booking_1 = new Booking(booking.getBooking_date(),price,booking.getCategory(),booking.getStatus());
		Booking booking_saved =  bookingRepository.save(booking_1);//me yeh nahi chala toh findById
		user.setBooking(booking_saved);//me Updating
		userRepository.save(user);//	saving after updating
		return booking_saved;//me 
		
	}

	private double doCalculation(Date bdate, User user) {
		// TODO Auto-generated method stub
		double price=1000;
		
		if(user.getBooking()==null) {
			return price;
		}
		else {
			long diffInDays=(bdate.getTime()-user.getBooking().getBooking_date().getTime())/(1000*60*60*24);
			System.out.println("Difference in Previous booking Date and Current booking date :"+diffInDays +" days...");
			if(diffInDays <=30 && diffInDays>=0 ) {
				
				price+=500;
			}
			else if(diffInDays>30 || (diffInDays<0 && diffInDays>-30)) {
				
				price=1000;
			}
				
		}
		
		System.out.println(price);
		return price;
	}
	
}
