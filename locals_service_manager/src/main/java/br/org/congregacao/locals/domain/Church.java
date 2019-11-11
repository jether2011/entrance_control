package br.org.congregacao.locals.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.azam.ulidj.ULID;

@Document(collection = "churches")
public final class Church implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private String id = ULID.random();
    @Indexed(unique = true)
    private String name;
    @Indexed(unique = true)
    private String code;
    private String description;
    private Set<MeetingRoom> rooms = new HashSet<>();
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;
    
    public Church() {
		this.created = LocalDateTime.now();
		this.updated = LocalDateTime.now();
	}
    
    private Church(final String name, final String code, final String description) {
    	this.code = code;
    	this.name = name;
    	this.description = description;
    	
    	this.created = LocalDateTime.now();
		this.updated = LocalDateTime.now();
    }
    
    public static Church of(final String name, final String code, final String description) {
    	return new Church(name, code, description);
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
	
	public String getDescription() {
		return description;
	}
	
	public Set<MeetingRoom> getRooms() {
		return Collections.unmodifiableSet(rooms);
	}
	
	public void addMeetingRoom(final MeetingRoom meetingRoom) {
		this.rooms.add(meetingRoom);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(code, id, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Church other = (Church) obj;
		return Objects.equals(code, other.code) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
