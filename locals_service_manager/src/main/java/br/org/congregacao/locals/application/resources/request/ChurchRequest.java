package br.org.congregacao.locals.application.resources.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.org.congregacao.locals.domain.Church;

public final class ChurchRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@NotBlank
	private String code;
	@NotNull
	@NotBlank
	private String description;
	
	public static Church from(final ChurchRequest request) {
		return Church.of(request.getName(), request.getCode(), request.getDescription());
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
