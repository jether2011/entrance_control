package br.org.congregacao.locals.application.resources.request;

import br.org.congregacao.locals.domain.Administration;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public final class AdministrationRequest implements Serializable {

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String cnpj;

    public static Administration from(final AdministrationRequest request) {
        return Administration.of(request.getName(), request.getCnpj());
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }
}
