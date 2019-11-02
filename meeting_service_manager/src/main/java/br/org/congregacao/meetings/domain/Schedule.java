package br.org.congregacao.meetings.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.azam.ulidj.ULID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "schedules")
public final class Schedule implements Serializable {

    @Id
    private String id = ULID.random();

    private String status;

    @Indexed(unique = true)
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
        this.closedAt = LocalDateTime.now();
    }

    private Schedule(final String status, final String operatorUser, final String meetingId) {
        this.status = status;
        this.operatorUser = operatorUser;
        this.meetingId = meetingId;

        this.openedAt = LocalDateTime.now();
        this.closedAt = LocalDateTime.now();
    }

    public static Schedule of(final String status, final String operatorUser, final String meetingId) {
        return new Schedule(status, operatorUser, meetingId);
    }

    public void addCardNumber(final String cardNumber) {
        this.cardNumbers.add(cardNumber);
    }

    public String getId() { return id; }

    public String getStatus() { return status; }

    public String getOperatorUser() { return operatorUser; }

    public String getMeetingId() { return meetingId; }

    public Set<String> getCardNUmber() { return Collections.unmodifiableSet(cardNumbers); }

    public LocalDateTime getOpenedAt() { return openedAt; }

    public LocalDateTime getClosedAt() { return closedAt; }

}
