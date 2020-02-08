package br.org.congregacao.locals.application.errors.config;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public final class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String error;
	private String message;
	private int status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	
	private ErrorResponse(final String errorCode, final String errorMsg, final int status) {
		super();
		this.error = errorCode;
		this.message = errorMsg;
		this.status = status;
		this.timestamp = LocalDateTime.now();
	}
	
	public static ErrorResponse of(final String errorCode, final String errorMsg, final int status) {
		return new ErrorResponse(errorCode, errorMsg, status);
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
}
