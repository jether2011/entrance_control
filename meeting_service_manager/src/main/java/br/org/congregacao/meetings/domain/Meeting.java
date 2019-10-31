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

@Document(collection = "Meetings")
public final class Meeting implements Serializable {

    @Id
    private String id = ULID.random();

    @Indexed(unique = true)
    private String name;

    private String description;

    @Indexed(unique = true)
    private String churchName;

    @Indexed(unique = true)
    private String churchRoomName;

    private String churchCode;

    @Indexed(unique = true)
    private String createdByUser;

    @Indexed
    private Set<String> churchEntrances = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime openLimitAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closeLimitAt;

    public Meeting(){
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.openLimitAt = LocalDateTime.now();
        this.closeLimitAt = LocalDateTime.now();
    }

    private Meeting(final String name, final String description, final String churchCode,
                    final String churchName, final String churchRoomName, final String createdByUser){
        this.name = name;
        this.description = description;
        this.churchCode = churchCode;
        this.churchName = churchName;
        this.churchRoomName = churchRoomName;
        this.createdByUser = createdByUser;

        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.openLimitAt = LocalDateTime.now();
        this.closeLimitAt = LocalDateTime.now();
    }

    public static Meeting of(final String name, final String description, final String churchCode,
                             final String churchName, final String churchRoom, final String createdByUser){
        return new Meeting(name, description, churchCode, churchName, churchRoom, createdByUser);
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getChurchName() { return churchName; }

    public String getChurchRoomName() { return churchRoomName; }

    public String getChurchCode() { return churchCode; }

    public String getCreatedByUser() { return createdByUser; }

    public Set<String> getChurchEntrances() { return Collections.unmodifiableSet(churchEntrances); }

    public LocalDateTime getCreated() { return created; }

    public LocalDateTime getUpdated() { return updated; }

    public LocalDateTime getopenLimitAt() { return openLimitAt; }

    public LocalDateTime getcloseLimitAt() { return closeLimitAt; }

    public void setUpdated(final LocalDateTime updated) { this.updated = updated; }

    public void addChurchEntrances(final String entrance){
        this.churchEntrances.add(entrance);
    }
}