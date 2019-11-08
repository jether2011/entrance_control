package br.org.congregacao.meetings.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.azam.ulidj.ULID;

@Document(collection = "meetings")
public final class Meeting implements Serializable {

    @Id
    private String id = ULID.random();

    @Indexed(unique = true)
    private String name;

    private String description;

    private String churchName;

    private String churchRoom;

    private String churchCode;

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
                    final String churchName, final String churchRoom, final String createdByUser){
        this.name = name;
        this.description = description;
        this.churchCode = churchCode;
        this.churchName = churchName;
        this.churchRoom = churchRoom;
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

    public String getChurchRoom() { return churchRoom; }

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
    
    public Meeting addDescription(final String description) {
    	this.description = description;
    	return this;
    }

}