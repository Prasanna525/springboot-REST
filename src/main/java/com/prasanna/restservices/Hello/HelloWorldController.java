package com.prasanna.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	//SImple method
	//need URI /helloworld
	//GET
	//@RequestMapping(method= RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String heloWorld() {
		return "Hello World1";
	}
	
	@GetMapping("/helloworldBean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Prasanna","Kumar","Bengaluru");
	}

}
