package br.org.congregacao.meetings.application.resources.request;

import br.org.congregacao.meetings.domain.Meeting;
import br.org.congregacao.meetings.domain.Schedule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ScheduleRequest implements Serializable {

    @NotNull
    @NotBlank
    private String status;

    @NotNull
    @NotBlank
    private String operatorUser;

    @NotNull
    @NotBlank
    private String meetingId;

    public static Schedule from(final ScheduleRequest request){
        return Schedule.of(request.getStatus(), request.getOperatorUser(), request.getMeetingId());
    }

    public String getStatus() {
        return status;
    }

    public String getOperatorUser() {
        return operatorUser;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOperatorUser(String operatorUser) {
        this.operatorUser = operatorUser;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
}
