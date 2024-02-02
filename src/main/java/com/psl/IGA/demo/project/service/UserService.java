package com.psl.IGA.demo.project.service;

import com.psl.IGA.demo.project.dto.UserDto;
import com.psl.IGA.demo.project.model.User;

public interface UserService {

	
	User save(UserDto userDto);

	int otpGenerate(String email);
}
