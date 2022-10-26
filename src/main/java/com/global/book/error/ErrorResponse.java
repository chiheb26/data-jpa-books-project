package com.global.book.error;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

	private Boolean status;
	private String message;
	private LocalDateTime dateTime;
	private List<String> details;
	public ErrorResponse() {
	}
	public ErrorResponse(String message, List<String> details) {
		this.message = message;
		this.details = details;
		this.status = Boolean.FALSE;
		this.dateTime= LocalDateTime.now();
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	
}
