package com.inetum.appliBibliotheque.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiError {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	private HttpStatus status;
	private String message;
	
	public ApiError(HttpStatus status, String message) {
		this.status=status;
		this.message=message;
		this.timestamp=LocalDateTime.now();
	}

}
