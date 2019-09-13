package com.prasanna.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.restservices.dtos.UserMsDto;
import com.prasanna.restservices.mappers.UserMapper;
import com.prasanna.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUserDtos(){
		
		return userMapper.UsersToUsersDtos(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserDtoById(@PathVariable Long id) {
		
		return userMapper.userToUserDto(userRepository.findById(id).get()); 
	}

}
