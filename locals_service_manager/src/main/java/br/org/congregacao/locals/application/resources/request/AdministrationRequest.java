package br.org.congregacao.locals.application.resources.request;

import br.org.congregacao.locals.domain.Administration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public final class AdministrationRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String cnpj;

    public static Administration from(final AdministrationRequest request) {
        return Administration.of(request.getName(), request.getCnpj());
    }

    private String getCnpj() {
        return cnpj;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}
