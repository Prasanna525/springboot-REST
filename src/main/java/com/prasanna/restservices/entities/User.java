package com.prasanna.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//import com.fasterxml.jackson.annotation.JsonFilter;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@ApiModel(description="This model is to create a User")
@Entity
@Table(name = "USER")
//@JsonIgnoreProperties({"firstName","lastName"}) --- static filtering @JsonIgnoreProperties
//@JsonFilter(value = "userFilter")  --- used for MappingJacksonValue filtering
public class User extends ResourceSupport {
	
	@ApiModelProperty(notes=" Auto generated unique id", required=true, position = 1)
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long userId;
	
	@ApiModelProperty(notes=" User name should be in short format",example = "pKumar", required=false, position = 2)
	@Size(min=2,max=50)
	@NotEmpty(message="Username is mandatory field. Please provide username")
	@Column(name="USER_NAME", length=50, nullable=false, unique=true)
	@JsonView(Views.External.class)
	private String userName;
	
	@Size(min=2,max=50, message="Firstname should have atleast 2 characters")
	@Column(name="FIRST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String firstName;
	
	@Column(name="LAST_NAME", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String lastName;
	
	@Column(name="EMAIL_ADDRESS", length=50, nullable=false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name="ROLE", length=50, nullable=false)
	@JsonView(Views.Internal.class)
	private String role;
	
	@Column(name="SSN", length=50, nullable=false, unique=true)
	//@Column(name="SSN", length=50, nullable=true, unique=true)
	//@JsonIgnore  ---static filtering @JsonIgnore
	@JsonView(Views.Internal.class)
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name="ADDRESS")
	public String address;

	public User() {
		
	}

	
	public User(Long userId,
			@NotEmpty(message = "Username is mandatory field. Please provide username") String userName,
			@Size(min = 2, message = "Firstname should have atleast 2 characters") String firstName, String lastName,
			String email, String role, String ssn, List<Order> orders, String address) {
	
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	//optional for bean logging
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders
				+ ", address=" + address + "]";
	}


	
	
	
	
	
	
	
	
}
