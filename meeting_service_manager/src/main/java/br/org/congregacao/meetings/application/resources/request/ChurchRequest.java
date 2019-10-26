package br.org.congregacao.meetings.application.resources.request;

import br.org.congregacao.meetings.domain.Church;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

public class ChurchRequest implements Serializable {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String reportNumber;

    @NotNull
    @NotBlank
    private String meetingRoom;

    public static Church from(final ChurchRequest request){
        return Church.of(request.getName(), request.getReportNumber(), request.getMeetingRoom());
    }

    private String getName() { return name; }

    public void setName(String name) { this.name = name; }

    private String getReportNumber() { return reportNumber; }

    public void setReportNumber(String reportNumber) { this.reportNumber = reportNumber; }

    private String getMeetingRoom() { return meetingRoom; }

    public void setMeetingRoom(String meetingRoom) { this.meetingRoom = meetingRoom; }
}
