package com.prasanna.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.restservices.entities.Order;
import com.prasanna.restservices.entities.User;
import com.prasanna.restservices.exceptions.UserNotFoundException;
//import com.prasanna.restservices.repositories.OrderRepository;
import com.prasanna.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	//@Autowired
	//private OrderRepository orderRepository;
	
	@GetMapping("/{userId}/orders")
	public Resources<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found with id: "+ userId);
		}
		
		List<Order> allOrders =  userOptional.get().getOrders();
		Resources<Order> finalResources = new Resources<Order>(allOrders);
		
		return finalResources;
	}

}
