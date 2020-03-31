package br.org.congregacao.locals.application.resources;

import br.org.congregacao.locals.application.errors.exception.NotFoundException;
import br.org.congregacao.locals.application.resources.request.RegionalRequest;
import br.org.congregacao.locals.domain.Administration;
import br.org.congregacao.locals.domain.Regional;
import br.org.congregacao.locals.service.AdministrationService;
import br.org.congregacao.locals.service.RegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/regionals")
public class RegionalResource implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Autowired
    private RegionalService regionalService;
    @Autowired
    private AdministrationService administrationService;
    
    @GetMapping(produces = "application/vnd.regionals.v1+json")
    public ResponseEntity<List<Regional>> getAll() {
        return ResponseEntity.ok().body(regionalService.findAll());
    }

	@GetMapping(value = "/{id}", produces = "application/vnd.regional_details.v1+json")
    public ResponseEntity<Regional> getOne(@PathVariable final String id) {
        final Optional<Regional> optionalRegional = regionalService.findById(id);
        return ResponseEntity.ok().body(optionalRegional
            		.orElseThrow(() -> new NotFoundException(String.format("Regional %s not found", id))));
        }

    @PostMapping(produces = "application/vnd.regional_create.v1+json")
    public ResponseEntity<Regional> create(@RequestBody @Valid final RegionalRequest request, final UriComponentsBuilder uriBuilder) {
        final Regional regional = RegionalRequest.from(request);
        regionalService.save(regional);

        final URI uri = uriBuilder.path("/regionals/{id}").buildAndExpand(regional.getId()).toUri();
        return ResponseEntity.created(uri).body(regional);
    }

    @PatchMapping(value = "/{idRegional}/regional/{idAdministration}/administration", produces = "application/vnd.regional_partial_update.v1+json")
    public ResponseEntity<Regional> addAdministration(@PathVariable final String idRegional, @PathVariable final String idAdministration) {
        final Optional<Administration> optionalAdministration = administrationService.findById(idAdministration);
        final Optional<Regional> optionalRegional = regionalService.findById(idRegional);

        final Regional regional = optionalRegional
        		.orElseThrow(() -> new NotFoundException(String.format("Regional %s not found", idRegional)));
        final Administration administration = optionalAdministration
        		.orElseThrow(() -> new NotFoundException(String.format("Administration %s not found", idAdministration)));
        regional.addAdministration(administration);
        regionalService.save(regional);

        return ResponseEntity.accepted().body(regional);
    }
}
