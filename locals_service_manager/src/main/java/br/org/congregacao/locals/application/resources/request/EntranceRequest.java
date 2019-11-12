package br.org.congregacao.locals.application.resources.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public final class EntranceRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private List<String> entrances;
	
	public List<String> getEntrances() {
		return entrances;
	}
}
