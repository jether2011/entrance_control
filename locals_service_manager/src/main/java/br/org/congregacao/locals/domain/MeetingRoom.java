package br.org.congregacao.locals.domain;

import br.org.congregacao.locals.domain.types.EntranceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.azam.ulidj.ULID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "meeting_rooms")
public class MeetingRoom implements Serializable {

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

    private MeetingRoom(final String name, final String description, final String entrance) {
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();

        this.addEntrance(entrance);
    }

    public static MeetingRoom of(final String name, final String description, final String entrance) {
        return new MeetingRoom(name, description, entrance);
    }

    public void addEntrance(final String entrance) {
        this.entrances.add(Objects.requireNonNull(EntranceType.valueOf(entrance), "The Entrance Value was not found!"));
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
}
