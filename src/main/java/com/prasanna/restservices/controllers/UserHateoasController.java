package com.prasanna.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prasanna.restservices.entities.Order;
import com.prasanna.restservices.entities.User;
import com.prasanna.restservices.exceptions.UserNotFoundException;
//import com.prasanna.restservices.repositories.UserRepository;
import com.prasanna.restservices.services.UserService;

@RestController
@RequestMapping(value="hateoas/users")
@Validated
public class UserHateoasController {
	
	//@Autowired
	//private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Resources<User> getAllUsers() throws UserNotFoundException{
		
		List<User> allUsers = userService.getAllUsers();
		
		for(User user:allUsers) {
			//need the userId for self Linking
			Long userId = user.getUserId();
			//create a self link
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			user.add(selfLink);
			
			//Relationship link with getAllOrders
			Resources<Order> orders = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
			
			Link ordersLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		
		//self link for get all users
		Link selfLinkGetAllUsers = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		Resources<User> finalResources = new Resources<User>(allUsers, selfLinkGetAllUsers);
		
		return finalResources;
	}
	
	@GetMapping("/{id}")
	public Resource<User> getUserById(@PathVariable("id") @Min(1) Long id){
			
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			
			//need the userId for self Linking
			Long userId = user.getUserId();
			//create a self link
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
			//add link
			user.add(selfLink);
			
			Resource<User> finalResource = new Resource<User>(user);
			
			return finalResource;
		} 
		catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
