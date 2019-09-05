package com.example.easynotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Object fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, Long noteId) {
		super(String.format("%s not found with %s : '%s'", resourceName, fieldName, noteId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = noteId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

}
