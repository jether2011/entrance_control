package br.org.congregacao.meetings.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.azam.ulidj.ULID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "churches")
public final class Church implements Serializable {

    @Id
    private String id = ULID.random();

    @Indexed(unique = true)
    private String name;

    @Indexed(unique = true)
    private String reportNumber;

    @Indexed
    private Set<String> meetingRooms = new HashSet<String>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    public Church() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private Church(final String name, final String reportNumber) {
        this.name = name;
        this.reportNumber = reportNumber;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public static Church of(String requestName, final String name, final String reportNumber) {
        return new Church(name, reportNumber);
    }

    public void addMeetingRoom(final String meetingRoom) {
        this.meetingRooms.add(meetingRoom);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public Set<String> getMeetingRooms() { return Collections.unmodifiableSet(meetingRooms); }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
