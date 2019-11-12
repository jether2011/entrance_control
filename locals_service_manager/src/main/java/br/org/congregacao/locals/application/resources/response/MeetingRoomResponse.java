package br.org.congregacao.locals.application.resources.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.congregacao.locals.domain.MeetingRoom;

public final class MeetingRoomResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	private Set<EntranceResponse> entrances = new HashSet<>();

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updated;

	private MeetingRoomResponse(final String id, final String name, final String description, 
			final LocalDateTime created, final LocalDateTime updated, final Set<EntranceResponse> entrances) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.addEntranceResponse(entrances);
	}
	
	public static MeetingRoomResponse of(final MeetingRoom meetingRoom) {
		final Set<EntranceResponse> newEntrances = new HashSet<>();
		meetingRoom.getEntrances()
			.forEach(type -> {
				newEntrances.add(EntranceResponse.of(type.name(), type.getDescription()));	
			});
		
		return new MeetingRoomResponse(
				meetingRoom.getId(), 
				meetingRoom.getName(), 
				meetingRoom.getDescription(), 
				meetingRoom.getCreated(), 
				meetingRoom.getUpdated(),
				newEntrances
			);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Set<EntranceResponse> getEntrances() {
		return Collections.unmodifiableSet(entrances);
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}
	
	public void addEntranceResponse(final Set<EntranceResponse> entranceResponses) {
		this.entrances.addAll(entranceResponses);
	}

}