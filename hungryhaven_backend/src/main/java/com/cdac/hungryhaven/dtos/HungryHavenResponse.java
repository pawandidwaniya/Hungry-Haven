package com.app.dtos;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class HungryHavenResponse {
	public static enum Status {
		SUCCESS, FAIL, ERROR
	}
	
	private Status status;
	private Object data;
	private String message;
	
	// success ctor
	public HungryHavenResponse(Status status, Object data) {
		this.status = status;
		this.data = data;
	}
	
	// error ctor
	public HungryHavenResponse(Status status, String message) {
		this.status = status;
		this.message = message;
	}
	
	// full ctor
	public HungryHavenResponse(Status status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	// getters and setters
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ResponseEntity<HungryHavenResponse> success() {
		return ResponseEntity.ok(new HungryHavenResponse(Status.SUCCESS, null));
	}
	
	public static ResponseEntity<HungryHavenResponse> success(Object data) {
		return ResponseEntity.ok(new HungryHavenResponse(Status.SUCCESS, data));
	}
	
	public static ResponseEntity<HungryHavenResponse> success(String message, Object data) {
		return ResponseEntity.ok(new HungryHavenResponse(Status.SUCCESS, data, message));
	}
	
	public static ResponseEntity<HungryHavenResponse> fail(Object data) {
		return ResponseEntity.ok(new HungryHavenResponse(Status.FAIL, data));
	}

	public static ResponseEntity<HungryHavenResponse> fail(String message, Object data) {
		return ResponseEntity.ok(new HungryHavenResponse(Status.FAIL, data, message));
	}
	
	public static ResponseEntity<HungryHavenResponse> error(String message) {
		return ResponseEntity.ok(new HungryHavenResponse(Status.ERROR, message));
	}

	public static ResponseEntity<HungryHavenResponse> error(String message, Object data) {
		return ResponseEntity.ok(new HungryHavenResponse(Status.ERROR, data, message));
	}
	
}
