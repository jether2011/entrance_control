package br.org.congregacao.locals.application.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

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

import br.org.congregacao.locals.application.resources.request.EntranceRequest;
import br.org.congregacao.locals.application.resources.request.MeetingRoomRequest;
import br.org.congregacao.locals.application.resources.response.MeetingRoomResponse;
import br.org.congregacao.locals.domain.MeetingRoom;
import br.org.congregacao.locals.service.MeetingRoomService;

@RestController
@RequestMapping(value = "/api/v1/meeting-rooms")
public class MeetingRoomResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MeetingRoomService meetingRoomService;

	@GetMapping
	public ResponseEntity<LinkedList<MeetingRoomResponse>> findAllMeetingRooms() {
		final LinkedList<MeetingRoomResponse> response = 
				meetingRoomService.findAll()
				.stream()
				.map(meeting -> MeetingRoomResponse.of(meeting))
				.collect(Collectors.toCollection(LinkedList::new));
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{meetingRoomId}")
	public ResponseEntity<MeetingRoomResponse> findOne(@PathVariable final String meetingRoomId) {
		final Optional<MeetingRoom> meetingRoomOptional = meetingRoomService.findById(meetingRoomId);
		
		if (meetingRoomOptional.isPresent()) {
			return ResponseEntity.ok().body(MeetingRoomResponse.of(meetingRoomOptional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
    public ResponseEntity<MeetingRoom> create(@RequestBody @Valid final MeetingRoomRequest request, final UriComponentsBuilder uriBuilder) {
        final MeetingRoom meetingRoom = MeetingRoomRequest.from(request);
        meetingRoomService.save(meetingRoom);

        final URI uri = uriBuilder.path("/api/v1/meeting-rooms/{id}").buildAndExpand(meetingRoom.getId()).toUri();
        return ResponseEntity.created(uri).body(meetingRoom);
    }
	
	@PatchMapping("/{meetingRoomId}/entrance")
	public ResponseEntity<MeetingRoom> addEntrancesToMeetingRoom(@RequestBody @Valid final EntranceRequest request, @PathVariable final String meetingRoomId, final UriComponentsBuilder uriBuilder) {
		final Optional<MeetingRoom> meetingRoomOptional = meetingRoomService.findById(meetingRoomId);
		
		if (meetingRoomOptional.isPresent()) {
			final MeetingRoom meetingRoomToUpdateEntrance = meetingRoomOptional.get();
			meetingRoomToUpdateEntrance.addEntrance(request.getEntrances());
			meetingRoomService.save(meetingRoomToUpdateEntrance);
			
			final URI uri = uriBuilder.path("/api/v1/meeting-rooms/{id}").buildAndExpand(meetingRoomToUpdateEntrance.getId()).toUri();
	        return ResponseEntity.created(uri).body(meetingRoomToUpdateEntrance);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
