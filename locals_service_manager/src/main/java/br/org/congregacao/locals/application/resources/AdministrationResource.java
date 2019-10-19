package br.org.congregacao.locals.application.resources;

import br.org.congregacao.locals.application.resources.request.AdministrationRequest;
import br.org.congregacao.locals.domain.Administration;
import br.org.congregacao.locals.service.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/administrations")
public class AdministrationResource implements Serializable {

    @Autowired
    private AdministrationService administrationService;

    public ResponseEntity<List<Administration>> getAll() {
        return ResponseEntity.ok().body(administrationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Administration> create(@RequestBody @Valid final AdministrationRequest request, final UriComponentsBuilder uriBuilder) {
        final Administration administration = AdministrationRequest.from(request);
        administrationService.save(administration);

        final URI uri = uriBuilder.path("/administrations/{id}").buildAndExpand(administration.getId()).toUri();
        return ResponseEntity.created(uri).body(administration);
    }

}
