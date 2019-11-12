package br.org.congregacao.locals.application.resources.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.org.congregacao.locals.domain.MeetingRoom;

public final class MeetingRoomRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@NotBlank
	private String description;
	
	public static MeetingRoom from(final MeetingRoomRequest request) {
		return MeetingRoom.of(request.getName(), request.getDescription());
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
