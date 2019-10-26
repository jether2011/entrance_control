package br.org.congregacao.meetings.application.resources;

import br.org.congregacao.meetings.domain.Meeting;
import br.org.congregacao.meetings.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/meetings")
public class MeetingResource implements Serializable {

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
}
