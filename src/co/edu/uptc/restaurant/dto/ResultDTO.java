package co.edu.uptc.restaurant.dto;

import java.util.ArrayList;

import java.util.List;

public class ResultDTO {
	private boolean isSuccessful;
	private String message;
	private List<String> listMessageError;
	
	public ResultDTO() {
		this.listMessageError = new ArrayList<>();
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}
	
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	
	public List<String> getListMessageError() {
		return listMessageError;
	}

	
	public void setListMessageError(List<String> listMessageError) {
		this.listMessageError = listMessageError;
	}
	
}
