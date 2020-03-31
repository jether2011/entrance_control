package br.org.congregacao.locals.application.resources.response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.org.congregacao.locals.domain.Church;
import br.org.congregacao.locals.domain.MeetingRoom;

public final class ChurchMeetingRoomsResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
    private String name;
    private String code;
    private Set<MeetingRoom> rooms = new HashSet<>(); 
    
    private ChurchMeetingRoomsResponse(final Church church) {
		this.id = church.getId();
		this.name = church.getName();
		this.code = church.getCode();
		this.rooms = church.getRooms();
	}

    public static ChurchMeetingRoomsResponse from(final Church church) {
    	return new ChurchMeetingRoomsResponse(church);
    }
    
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public Set<MeetingRoom> getRooms() {
		return rooms;
	}
}
