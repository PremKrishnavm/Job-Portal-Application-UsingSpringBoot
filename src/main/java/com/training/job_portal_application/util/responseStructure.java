package com.training.job_portal_application.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class responseStructure<T> {
	
	private int statusCode;
	private String message;
	private Object data;

}
