package br.org.congregacao.meetings.application.resources.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.org.congregacao.meetings.domain.Schedule;
import br.org.congregacao.meetings.domain.enums.StatusType;

public class ScheduleRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
    private Integer status;

    @NotNull
    @NotBlank
    private String operatorUser;

    @NotNull
    @NotBlank
    private String meetingId;

    public static Schedule from(final ScheduleRequest request){
        return Schedule.of(StatusType.fromId(request.getStatus()), request.getOperatorUser(), request.getMeetingId());
    }

    public Integer getStatus() {
        return status;
    }

    public String getOperatorUser() {
        return operatorUser;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setOperatorUser(String operatorUser) {
        this.operatorUser = operatorUser;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
}
