package br.org.congregacao.meetings.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.congregacao.meetings.domain.enums.StatusType;
import io.azam.ulidj.ULID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "schedules")
public final class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private String id = ULID.random();

    private StatusType status;

    @Indexed
    private String operatorUser;

    @Indexed(unique = true)
    private String meetingId;

    @Indexed
    private Set<String> cardNumbers = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime openedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedAt;

    public Schedule(){
        this.openedAt = LocalDateTime.now();
    }

    private Schedule(final StatusType status, final String operatorUser, final String meetingId) {
        this.status = status;
        this.operatorUser = operatorUser;
        this.meetingId = meetingId;
        this.openedAt = LocalDateTime.now();
    }

    public static Schedule of(final StatusType status, final String operatorUser, final String meetingId) {
        return new Schedule(status, operatorUser, meetingId);
    }

    public void addCardNumber(final String cardNumber) {
        this.cardNumbers.add(cardNumber);
    }
    
    public Schedule addStatus(final Integer status) {
    	this.status = StatusType.toEnum(status);
    	return this;
    }

    public String getId() { return id; }

    public StatusType getStatus() { return status; }

    public String getOperatorUser() { return operatorUser; }

    public String getMeetingId() { return meetingId; }

    public Set<String> getCardNUmber() { return Collections.unmodifiableSet(cardNumbers); }

    public LocalDateTime getOpenedAt() { return openedAt; }

    public LocalDateTime getClosedAt() { return closedAt; }

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
		Schedule other = (Schedule) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
