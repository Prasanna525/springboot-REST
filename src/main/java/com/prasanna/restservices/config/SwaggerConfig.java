package com.prasanna.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.prasanna.restservices"))
				.paths(PathSelectors.ant("/users/**"))  //this selects only User and Order controller
				.build();
	}
	
	//Swagger metadata URL : http://localhost:8080/v2/api-docs
	//Swagger UI URL       : 

	private ApiInfo getApiInfo() {
		
		return new ApiInfoBuilder()
				.title("DS User Management Service")
				.description("This page lists all API's of User Management")
				.version("2.0")
				.contact(new Contact("Prasanna","http://www.prasanna.com","prasanna.kumar525@gmail.com"))
				.license("Licence 2.0")
				.licenseUrl("http://www.prasanna.com/license.html")
				.build();
	}

}
