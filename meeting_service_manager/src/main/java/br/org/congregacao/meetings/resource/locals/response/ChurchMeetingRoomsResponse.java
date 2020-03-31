package br.org.congregacao.meetings.resource.locals.response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public final class ChurchMeetingRoomsResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String code;
	private Set<MeetingRoomResponse> rooms = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<MeetingRoomResponse> getRooms() {
		return rooms;
	}

	public void setRooms(Set<MeetingRoomResponse> rooms) {
		this.rooms = rooms;
	}
	
}

final class MeetingRoomResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}