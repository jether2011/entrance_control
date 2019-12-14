package br.org.congregacao.meetings.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.congregacao.meetings.application.resources.request.MeetingRequest;
import br.org.congregacao.meetings.domain.Meeting;
import br.org.congregacao.meetings.service.MeetingService;

@RestController
@RequestMapping(value = "/api/v1/meetings")
public class MeetingResource {

    @Autowired
    private MeetingService meetingService;

    @GetMapping
    public ResponseEntity<List<Meeting>> getAll() {
        return ResponseEntity.ok().body(meetingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getOne(@PathVariable final String id) {
        final Optional<Meeting> optionalMeeting = meetingService.findById(id);
        
        if (optionalMeeting.isPresent()) {
            return ResponseEntity.ok().body(optionalMeeting.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Meeting> create(@RequestBody @Valid final MeetingRequest request, final UriComponentsBuilder uriBuilder) {
        final Meeting meeting = MeetingRequest.from(request);
        meetingService.save(meeting);

        final URI uri = uriBuilder.path("/meeting/{id}").buildAndExpand(meeting.getId()).toUri();
        return ResponseEntity.created(uri).body(meeting);
    }
    
    @PatchMapping("/{meetingId}/description")
    public ResponseEntity<Meeting> addMeeting(@RequestBody final MeetingRequest request, @PathVariable final String meetingId) {
        final Optional<Meeting> optionalMeeting = meetingService.findById(meetingId);
        if (optionalMeeting.isPresent()) {
            final Meeting meeting = optionalMeeting.get();
            meeting.addDescription(request.getDescription());
            meetingService.save(meeting);
            return ResponseEntity.accepted().body(meeting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Meeting> delete(@PathVariable String id) {
        meetingService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
