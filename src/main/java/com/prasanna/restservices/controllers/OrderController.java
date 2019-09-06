package com.prasanna.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.restservices.entities.Order;
import com.prasanna.restservices.entities.User;
import com.prasanna.restservices.exceptions.UserNotFoundException;
import com.prasanna.restservices.repositories.OrderRepository;
import com.prasanna.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders by user id
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found with id: "+ userId);
		}
		
		return userOptional.get().getOrders();
	}
	
	//create order
	@PostMapping("{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found with id: "+ userId);
		}
		
		User user = userOptional.get();
		
		order.setUser(user);
		
		return orderRepository.save(order);	
		
	}
	
	@GetMapping("{userId}/orders/{orderId}")
	public Optional<Order> getOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found with id: "+userId);
		}
		
		return orderRepository.findById(orderId);
	}
}
