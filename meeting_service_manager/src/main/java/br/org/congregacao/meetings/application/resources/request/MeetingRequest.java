package br.org.congregacao.meetings.application.resources.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.congregacao.meetings.domain.Meeting;

public class MeetingRequest implements Serializable {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 512)
    private String description;

    @NotNull
    @NotBlank
    private String churchName;

    @NotNull
    @NotBlank
    private String churchRoom;

    @NotNull
    @NotEmpty
    private String churchCode;

    @NotNull
    @NotEmpty
    private String createdByUser;

    public static Meeting from(final MeetingRequest request){
        return Meeting.of(
                request.getName(),
                request.getDescription(),
                request.getChurchCode(),
                request.getChurchName(),
                request.getChurchRoom(),
                request.getCreatedByUser());
    }
    
    private String getName() { return name; }

    public void setName(String name) { this.name = name; }

    private String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    private String getChurchName() { return churchName; }

    public void setChurchName(String churchName) { this.churchName = churchName; }

    private String getChurchRoom() { return churchRoom; }

    public void setChurchRoom(String churchRoom) { this.churchRoom = churchRoom; }

    private String getChurchCode() { return churchCode; }

    public void setChurchCode(String churchCode) { this.churchCode = churchCode; }

    private String getCreatedByUser() { return createdByUser; }

    public void setCreatedByUser(String createdByUser) { this.createdByUser = createdByUser; }

}
