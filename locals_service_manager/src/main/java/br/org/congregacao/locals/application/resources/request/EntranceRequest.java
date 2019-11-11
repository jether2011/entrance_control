package br.org.congregacao.locals.application.resources.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.org.congregacao.locals.domain.types.EntranceType;

public final class EntranceRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private List<EntranceType> entrances;
	
	public List<EntranceType> getEntrances() {
		return entrances;
	}
}
