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

import br.org.congregacao.meetings.application.errors.exception.NotFoundException;
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
        return ResponseEntity.ok().body(optionalMeeting
            		.orElseThrow(() -> new NotFoundException(String.format("Meeting %s not found", id))));
    }
    
    @PostMapping
    public ResponseEntity<Meeting> create(@RequestBody @Valid final MeetingRequest request, final UriComponentsBuilder uriBuilder) {
        final Meeting meeting = MeetingRequest.from(request);
        meetingService.save(meeting);

        final URI uri = uriBuilder.path("/meeting/{id}").buildAndExpand(meeting.getId()).toUri();
        return ResponseEntity.created(uri).body(meeting);
    }
    
    @PatchMapping("/{meetingId}/open-meeting")
    public ResponseEntity<Meeting> openMeeting(@PathVariable final String meetingId) {
        final Optional<Meeting> optionalMeeting = meetingService.findById(meetingId);
        
        final Meeting meeting = optionalMeeting
        		.orElseThrow(() -> new NotFoundException(String.format("Meeting %s not found", meetingId)));
        meeting.openMeeting();
        meetingService.save(meeting);
        return ResponseEntity.accepted().body(meeting);
    }
    
    @PatchMapping("/{meetingId}/close-meeting")
    public ResponseEntity<Meeting> closeMeeting(@PathVariable final String meetingId) {
        final Optional<Meeting> optionalMeeting = meetingService.findById(meetingId);
        
        final Meeting meeting = optionalMeeting
        		.orElseThrow(() -> new NotFoundException(String.format("Meeting %s not found", meetingId)));
        meeting.closeMeeting();
        meetingService.save(meeting);
        return ResponseEntity.accepted().body(meeting);
    }
    
    @PatchMapping("/{meetingId}/cancel-meeting")
    public ResponseEntity<Meeting> cancelMeeting(@PathVariable final String meetingId) {
        final Optional<Meeting> optionalMeeting = meetingService.findById(meetingId);
        
        final Meeting meeting = optionalMeeting
        		.orElseThrow(() -> new NotFoundException(String.format("Meeting %s not found", meetingId)));
        meeting.cancelMeeting();
        meetingService.save(meeting);
        return ResponseEntity.accepted().body(meeting);
    }
    
    @PatchMapping("/{meetingId}/add-participant/{cardNumber}")
    public ResponseEntity<Meeting> addParticipant(@PathVariable final String meetingId, @PathVariable final String cardNumber) {
        final Optional<Meeting> optionalMeeting = meetingService.findById(meetingId);
        
        final Meeting meeting = optionalMeeting
        		.orElseThrow(() -> new NotFoundException(String.format("Meeting %s not found", meetingId)));
        meeting.addCardNumber(cardNumber);
        
        meetingService.save(meeting);
        return ResponseEntity.accepted().body(meeting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Meeting> delete(@PathVariable String id) {
        meetingService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
