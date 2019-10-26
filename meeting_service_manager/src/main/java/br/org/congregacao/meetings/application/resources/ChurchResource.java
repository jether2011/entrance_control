package br.org.congregacao.meetings.application.resources;

import br.org.congregacao.meetings.application.resources.request.ChurchRequest;
import br.org.congregacao.meetings.application.resources.request.MeetingRequest;
import br.org.congregacao.meetings.domain.Church;
import br.org.congregacao.meetings.domain.Meeting;
import br.org.congregacao.meetings.service.ChurchService;
import br.org.congregacao.meetings.service.MeetingService;
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
@RequestMapping(value = "/api/v1/church")
public class ChurchResource implements Serializable {

    @Autowired
    private ChurchService churchService;

    @GetMapping
    public ResponseEntity<List<Church>> getAll() {
        return ResponseEntity.ok().body(churchService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Church> getOne(@PathVariable final String id) {
        final Optional<Church> optionalChurch = churchService.findById(id);

        if (optionalChurch.isPresent()) {
            return ResponseEntity.ok().body(optionalChurch.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Church> create(@RequestBody @Valid final ChurchRequest request, final UriComponentsBuilder uriBuilder) {
        final Church church = ChurchRequest.from(request);
        churchService.save(church);

        final URI uri = uriBuilder.path("/church/{id}").buildAndExpand(church.getId()).toUri();
        return ResponseEntity.created(uri).body(church);
    }
    
}
