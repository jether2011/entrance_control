package br.org.congregacao.meetings.domain.enums;

import java.util.Arrays;

public enum StatusType {
	
	SCHEDULED(1, "Agendada"),
	IN_PROGRESS(2, "Em Andamento"),
	FINISHED(3, "Finalizada'"),
	CANCELED(4, "Cancelada");
	
	private int id;
	private String description;
	
	private StatusType(final int id, final String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static StatusType fromId(final int id) {
		return Arrays.stream(StatusType.values())
				.filter(status-> status.id == id)
				.findFirst().get();
	}

}
