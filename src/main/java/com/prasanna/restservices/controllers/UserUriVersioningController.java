package com.prasanna.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.restservices.dtos.UserDtoV1;
import com.prasanna.restservices.dtos.UserDtoV2;
import com.prasanna.restservices.entities.User;
import com.prasanna.restservices.exceptions.UserNotFoundException;
import com.prasanna.restservices.services.UserService;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
			Optional<User> optionalUser = userService.getUserById(id);
			
			if(!optionalUser.isPresent()) {
				throw new UserNotFoundException("User not found.");
			}
			
			User user = optionalUser.get();
			
			UserDtoV1 userDto = modelMapper.map(user, UserDtoV1.class);
			
			return userDto;
	}
	
	@GetMapping("/v2.0/{id}")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
			Optional<User> optionalUser = userService.getUserById(id);
			
			if(!optionalUser.isPresent()) {
				throw new UserNotFoundException("User not found.");
			}
			
			User user = optionalUser.get();
			
			UserDtoV2 userDto = modelMapper.map(user, UserDtoV2.class);
			
			return userDto;
	}

}
