package br.org.congregacao.meetings.resource.locals.response;

import java.io.Serializable;

public final class WorkGroupResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
    private String name;
    private String description;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
