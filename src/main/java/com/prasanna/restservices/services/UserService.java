package com.prasanna.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prasanna.restservices.entities.User;
import com.prasanna.restservices.exceptions.UserExistsException;
import com.prasanna.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		
		User existingUser = userRepository.findByUserName(user.getUserName());
		
		if(existingUser!=null) {
			throw new UserExistsException("User already exists in repository");
		}
		
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		//return type of findById is Optional(its a container object)
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in User repository");
		}
		
		return user;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		
		Optional<User> userToUpdate = userRepository.findById(id);
		
		if(!userToUpdate.isPresent()) {
			throw new UserNotFoundException("User not found in the repository, provide existing user to update");
		}
		
		user.setUserId(id);;
		
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		
		Optional<User> userToDelete = userRepository.findById(id);
		
		if(!userToDelete.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in the repository, provide existing user to delete");
		}
		
			userRepository.deleteById(id);
	}
	
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
