package br.org.congregacao.locals.application.resources.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.congregacao.locals.domain.WorkGroup;

public final class WorkGroupRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
    @NotBlank
	private String name;
	@NotNull
    @NotBlank
    @Size(max = 512)
    private String description;
    
    public static WorkGroup from(final WorkGroupRequest request) {
    	return WorkGroup.of(request.getName(), request.description);
    }

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
