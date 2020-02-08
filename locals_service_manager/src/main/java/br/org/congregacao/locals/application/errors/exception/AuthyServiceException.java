package br.org.congregacao.locals.application.errors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AuthyServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public AuthyServiceException(String message) {
        super(message);
    }
}
