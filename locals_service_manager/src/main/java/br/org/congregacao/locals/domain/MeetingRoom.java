package br.org.congregacao.locals.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.congregacao.locals.domain.types.EntranceType;
import io.azam.ulidj.ULID;

@Document(collection = "meeting_rooms")
public class MeetingRoom implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    private String id = ULID.random();
    @Indexed(unique = true)
    private String name;
    private String description;
    @Indexed
    private Set<EntranceType> entrances = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    public MeetingRoom() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private MeetingRoom(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public static MeetingRoom of(final String name, final String description) {
        return new MeetingRoom(name, description);
    }

    public void addEntrance(final List<String> entrances) {
    	this.updated = LocalDateTime.now();
    	entrances.forEach(entrance -> {
    		this.entrances.add(Objects.requireNonNull(EntranceType.valueOf(entrance)
    				, "The Entrance Value was not found!"));
    	});
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

    public Set<EntranceType> getEntrances() {
        return Collections.unmodifiableSet(entrances);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(final LocalDateTime updated) {
        this.updated = updated;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingRoom other = (MeetingRoom) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
    
}
