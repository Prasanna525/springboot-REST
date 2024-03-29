package com.prasanna.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
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
	
	@GetMapping("/hello-I18n")
	public String getMessagesInI18nFormat(@RequestHeader(name ="Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/hello-I18n2")
	public String getMessagesInI18nFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}

}
