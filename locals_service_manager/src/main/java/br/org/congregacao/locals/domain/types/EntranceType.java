package br.org.congregacao.locals.domain.types;

public enum EntranceType {

    MAIN_ENTRANCE("Entrada Principal");
	
	private final String type;
	
	private EntranceType(final String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

}
