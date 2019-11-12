package br.org.congregacao.locals.domain.types;

public enum EntranceType {

    MAIN_ENTRANCE("Entrada Principal");
	
	private final String description;
	
	private EntranceType(final String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
