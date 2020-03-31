package br.org.congregacao.locals.application.resources.response;

import java.io.Serializable;

import br.org.congregacao.locals.domain.WorkGroup;

public final class WorkGroupResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
    private String name;
    private String description;
    
    private WorkGroupResponse(final WorkGroup workGroup) {
    	this.id = workGroup.getId();
    	this.name = workGroup.getName();
    	this.description = workGroup.getDescription();
    }
    
    public static WorkGroupResponse from(final WorkGroup workGroup) {
    	return new WorkGroupResponse(workGroup);
    }

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
