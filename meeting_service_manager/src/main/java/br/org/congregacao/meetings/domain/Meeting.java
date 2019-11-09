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

	private static final long serialVersionUID = 1L;

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
    private LocalDateTime open;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime openLimitAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closeLimitAt;

    public Meeting(){
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private Meeting(final String name, final String description, final String churchCode,
                    final String churchName, final String churchRoom, final String createdByUser, 
                    final LocalDateTime openLimitAt, final LocalDateTime closeLimitAt, final LocalDateTime open){
        this.name = name;
        this.description = description;
        this.churchCode = churchCode;
        this.churchName = churchName;
        this.churchRoom = churchRoom;
        this.createdByUser = createdByUser;

        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.openLimitAt = openLimitAt;
        this.closeLimitAt = closeLimitAt;
        this.open = open;
    }
    
    public static Meeting of(final String name, final String description, final String churchCode,
                             final String churchName, final String churchRoom, final String createdByUser, 
                             final LocalDateTime openLimitAt, final LocalDateTime closeLimitAt, final LocalDateTime open){
        return new Meeting(name, description, churchCode, churchName, churchRoom, createdByUser, openLimitAt, closeLimitAt, open);
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

    public LocalDateTime getOpenLimitAt() { return openLimitAt; }

    public LocalDateTime getCloseLimitAt() { return closeLimitAt; }
    
    public LocalDateTime getOpen() { return open; }

    public void setUpdated(final LocalDateTime updated) { this.updated = updated; }

    public void addChurchEntrances(final String entrance){ this.churchEntrances.add(entrance); }
    
    public Meeting addDescription(final String description) {
    	this.description = description;
    	return this;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meeting other = (Meeting) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}