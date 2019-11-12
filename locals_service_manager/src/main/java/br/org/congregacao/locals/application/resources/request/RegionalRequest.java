package br.org.congregacao.locals.application.resources.request;

import br.org.congregacao.locals.domain.Regional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public final class RegionalRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    private String initial;
    @NotNull
    @NotBlank
    @Size(max = 512)
    private String description;

    public static Regional from(final RegionalRequest request) {
        return Regional.of(request.getInitial(), request.getDescription());
    }

    private String getInitial() { return initial; }

    private String getDescription() { return description; }

    public void setInitial(String initial) { this.initial = initial; }

    public void setDescription(String description) { this.description = description; }
}
