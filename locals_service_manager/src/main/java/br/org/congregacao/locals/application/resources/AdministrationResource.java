package br.org.congregacao.locals.application.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.congregacao.locals.application.errors.exception.NotFoundException;
import br.org.congregacao.locals.application.resources.request.AdministrationRequest;
import br.org.congregacao.locals.domain.Administration;
import br.org.congregacao.locals.domain.Church;
import br.org.congregacao.locals.service.AdministrationService;
import br.org.congregacao.locals.service.ChurchService;

@RestController
@RequestMapping(value = "/api/v1/administrations")
public class AdministrationResource implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Autowired
    private AdministrationService administrationService;
    @Autowired
    private ChurchService churchService;

    @GetMapping
    public ResponseEntity<List<Administration>> getAll() {
        return ResponseEntity.ok().body(administrationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administration> getOne(@PathVariable final String id) {
        final Optional<Administration> optionalAdministration = administrationService.findById(id);
        return ResponseEntity.ok().body(optionalAdministration
        		.orElseThrow(() -> new NotFoundException(String.format("Administration %s not found", id))));
    }

    @PostMapping
    public ResponseEntity<Administration> create(@RequestBody @Valid final AdministrationRequest request, final UriComponentsBuilder uriBuilder) {
        final Administration administration = AdministrationRequest.from(request);
        administrationService.save(administration);

        final URI uri = uriBuilder.path("/api/v1/administrations/{id}").buildAndExpand(administration.getId()).toUri();
        return ResponseEntity.created(uri).body(administration);
    }
    
    @PatchMapping("/{administrationId}/church/{churchId}")
    public ResponseEntity<Administration> addAdministration(@PathVariable final String administrationId, @PathVariable final String churchId) {
        final Optional<Administration> optionalAdministration = administrationService.findById(administrationId);
        final Optional<Church> churchOptional = churchService.findById(churchId);

        
        final Administration administration = optionalAdministration
        		.orElseThrow(() -> new NotFoundException(String.format("Administration %s not found", administrationId)));
        final Church church = churchOptional
        		.orElseThrow(() -> new NotFoundException(String.format("Church %s not found", churchId)));
        administration.addChurch(church);
        
        administrationService.save(administration);
        return ResponseEntity.accepted().body(administration);
        
    }

}
