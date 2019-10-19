package br.org.congregacao.locals.application.resources.request;

import br.org.congregacao.locals.domain.Regional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public final class RegionalRequest implements Serializable {

    @NotNull
    @NotEmpty
    private String initial;
    @NotNull
    @NotEmpty
    @Size(max = 512)
    private String description;

    public static Regional from(final RegionalRequest request) {
        return Regional.of(request.initial, request.description);
    }
}
