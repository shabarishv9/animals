/**
 * 
 */
package com.shabarish.boot.animals.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author shabarish
 *
 */
public class AnimalResponseModel {
	
	private String message;
	private List<Animal> data = new ArrayList<>();
	private String status;
	private String error="NA";
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Animal> getData() {
		return data;
	}
	public void setData(List<Animal> data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	

}
