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

import br.org.congregacao.meetings.application.errors.exception.BadRequestException;
import br.org.congregacao.meetings.domain.enums.StatusType;
import io.azam.ulidj.ULID;

@Document(collection = "meetings")
public final class Meeting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private String id = ULID.random();

    @Indexed(unique = true)
    private String name;
    
    @Indexed
    private StatusType status;

    private String description;

    private String administrationId;
    
    private String churchId;
    
    private String churchName;

    private String churchRoom;

    private String churchCode;

    private String createdByUser;
    
    @Indexed
    private Set<String> cardNumbers = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closeAt;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime open;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime openLimitAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closeLimitAt;

    public Meeting(){
    	this.status = StatusType.SCHEDULED;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private Meeting(final String name, final String description, final String churchCode,
                    final String churchName, final String churchRoom, final String createdByUser, 
                    final LocalDateTime openLimitAt, final LocalDateTime closeLimitAt,
                    final String churchId, final String administrationId){
        this.status = StatusType.SCHEDULED;
    	this.name = name;
        this.description = description;
        this.administrationId = administrationId;
        this.churchId = churchId;
        this.churchCode = churchCode;
        this.churchName = churchName;
        this.churchRoom = churchRoom;
        this.createdByUser = createdByUser;

        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
        this.openLimitAt = openLimitAt;
        this.closeLimitAt = closeLimitAt;
    }
    
    public static Meeting of(final String name, final String description, final String churchCode,
                             final String churchName, final String churchRoom, final String createdByUser, 
                             final LocalDateTime openLimitAt, final LocalDateTime closeLimitAt,
                             final String churchId, final String administrationId){
        return new Meeting(name, description, churchCode, churchName, churchRoom, 
        		createdByUser, openLimitAt, closeLimitAt, churchId, administrationId);
    }
    
    public String getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }
    
    public String getAdministrationId() { return administrationId; }
    
    public String getChurchId() { return churchId; }

    public String getChurchName() { return churchName; }

    public String getChurchRoom() { return churchRoom; }

    public String getChurchCode() { return churchCode; }

    public String getCreatedByUser() { return createdByUser; }
    
    public Set<String> getCardNumbers() { return Collections.unmodifiableSet(cardNumbers); }

    public LocalDateTime getCreated() { return created; }

    public LocalDateTime getUpdated() { return updated; }

    public LocalDateTime getOpenLimitAt() { return openLimitAt; }

    public LocalDateTime getCloseLimitAt() { return closeLimitAt; }
    
    public LocalDateTime getOpen() { return open; }

    public LocalDateTime getCloseAt() { return closeAt; }
    
    public StatusType getStatus() { return status; }
    
    public void addCardNumber(final String cardNumber) {
    	if(!this.status.equals(StatusType.IN_PROGRESS))
    		throw new BadRequestException(String.format("A participant can not be added in a meeting with status %s", this.status));
    	
        if(this.cardNumbers.contains(cardNumber))
        	throw new BadRequestException(String.format("The participant with card number [ %s ] is already added in the meeting", cardNumber));
    	
    	this.cardNumbers.add(cardNumber);
    }
    
    public Meeting openMeeting() {
    	if(!this.status.equals(StatusType.SCHEDULED))
    		throw new BadRequestException(String.format("The Meeting can not be opened because the status is %s", this.status));
    		
    	this.status = StatusType.IN_PROGRESS;
    	this.updated = LocalDateTime.now();
    	this.open = LocalDateTime.now();
    	return this;
    }

    public Meeting closeMeeting() {
    	if(!this.status.equals(StatusType.IN_PROGRESS))
    		throw new BadRequestException(String.format("The Meeting can not be closed because the status is %s", this.status));
    	
    	this.status = StatusType.FINISHED;
    	this.closeAt = LocalDateTime.now();
    	this.updated = LocalDateTime.now();
    	return this;
    }
    
    public Meeting cancelMeeting() {
    	if(!this.status.equals(StatusType.SCHEDULED))
    		throw new BadRequestException(String.format("The Meeting can not be canceled because the status is %s", this.status));
    	
    	this.status = StatusType.CANCELED;
    	this.closeAt = LocalDateTime.now();
    	this.updated = LocalDateTime.now();
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