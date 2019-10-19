package br.org.congregacao.locals.application.resources;

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
@RequestMapping(value = "/api/v1/regionals")
public class RegionalResource implements Serializable {

    @Autowired
    private RegionalService regionalService;
    @Autowired
    private AdministrationService administrationService;

    @GetMapping
    public ResponseEntity<List<Regional>> getAll() {
        return ResponseEntity.ok().body(regionalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regional> getOne(@PathVariable final String id) {
        final Optional<Regional> optionalRegional = regionalService.findById(id);

        if (optionalRegional.isPresent()) {
            return ResponseEntity.ok().body(optionalRegional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Regional> create(@RequestBody @Valid final RegionalRequest request, final UriComponentsBuilder uriBuilder) {
        final Regional regional = RegionalRequest.from(request);
        regionalService.save(regional);

        final URI uri = uriBuilder.path("/regionals/{id}").buildAndExpand(regional.getId()).toUri();
        return ResponseEntity.created(uri).body(regional);
    }

    @PatchMapping("/{idRegional}/regional/{idAdministration}/administration")
    public ResponseEntity<Regional> addAdministration(@PathVariable final String idRegional, @PathVariable final String idAdministration) {
        final Optional<Administration> optionalAdministration = administrationService.findById(idAdministration);
        final Optional<Regional> optionalRegional = regionalService.findById(idRegional);

        if (optionalRegional.isPresent() && optionalAdministration.isPresent()) {
            final Regional regional = optionalRegional.get();
            final Administration administration = optionalAdministration.get();
            regional.addAdministration(administration);
            regionalService.save(regional);

            return ResponseEntity.accepted().body(regional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
