package com.prasanna.restservices.exceptions;

import java.util.Date;

public class CustomErrorDetails {
	
	private Date timeStamp;
	private String message;
	private String errordetails;
	
	public CustomErrorDetails(Date timeStamp, String message, String errordetails) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.errordetails = errordetails;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getErrordetails() {
		return errordetails;
	}

	
	
	

}
