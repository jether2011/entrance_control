package br.org.congregacao.locals.application.resources.response;

import java.io.Serializable;

final class EntranceResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;
	private final String description;

	private EntranceResponse(final String name, final String description) {
		this.name = name;
		this.description = description;
	}
	
	public static EntranceResponse of(final String name, final String description) {
		return new EntranceResponse(name, description);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}