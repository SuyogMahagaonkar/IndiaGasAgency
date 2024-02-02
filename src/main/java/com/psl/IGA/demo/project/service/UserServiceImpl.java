package com.psl.IGA.demo.project.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.IGA.demo.project.dto.UserDto;
import com.psl.IGA.demo.project.model.User;
import com.psl.IGA.demo.project.repositories.UserRepository;

import jakarta.mail.internet.MimeMessage;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender eMailSender;
	
	
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getName(),userDto.getMobile(),userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),"USER",userDto.getAddress(),userDto.getBooking());
		
		return userRepository.save(user);
	}
	@Override
	public int otpGenerate(String email) {
		int otp= ThreadLocalRandom.current().nextInt(999, 10000);
		
		
		System.out.println(otp);
		System.out.println(email);
		try {
			MimeMessage message=eMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			helper.setTo(email);
			helper.setSubject("Your OTP for IGA");
			helper.setText("Your OTP is : "+ otp,true);
			
			eMailSender.send(message);
			System.out.println("OTP send Successfully");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Failed to send OTP ");
		}
		return otp;
	}
	
}
