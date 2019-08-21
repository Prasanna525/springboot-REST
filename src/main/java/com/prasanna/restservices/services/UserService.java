package com.prasanna.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasanna.restservices.entities.User;
import com.prasanna.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//getAllUsers
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}
	
	//create user 
	public User createUser(User user) {
		
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) {
		//return type of findById is Optional(its a container object)
		Optional<User> user = userRepository.findById(id);
		
		return user;
	}
	
	public User updateUserById(Long id, User user) {
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
