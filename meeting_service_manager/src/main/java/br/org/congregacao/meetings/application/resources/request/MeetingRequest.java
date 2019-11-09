package br.org.congregacao.meetings.application.resources.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.congregacao.meetings.domain.Meeting;

public class MeetingRequest implements Serializable {

   private static final long serialVersionUID = 1L;

	@NotNull
    @NotBlank
    private String name;

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
    
    @NotNull
    @NotEmpty
    private LocalDateTime openLimitAt;
    
    @NotNull
    @NotEmpty
    private LocalDateTime closeLimitAt;
    
    @NotNull
    @NotEmpty
    private LocalDateTime open;

    public static Meeting from(final MeetingRequest request){
        return Meeting.of(
                request.getName(),
                request.getDescription(),
                request.getChurchCode(),
                request.getChurchName(),
                request.getChurchRoom(),
                request.getCreatedByUser(),
                request.getOpenLimitAt(),
                request.getCloseLimitAt(),
                request.getOpen());
    }
    
    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setChurchName(String churchName) { this.churchName = churchName; }

    public void setChurchRoom(String churchRoom) { this.churchRoom = churchRoom; }

    public void setChurchCode(String churchCode) { this.churchCode = churchCode; }

    public void setCreatedByUser(String createdByUser) { this.createdByUser = createdByUser; }

	public void setOpen(LocalDateTime open) { this.open = open; }
	
	public void setOpenLimitAt(LocalDateTime openLimitAt) { this.openLimitAt = openLimitAt; }
	
	public void setCloseLimitAt(LocalDateTime closeLimitAt) { this.closeLimitAt = closeLimitAt; }
	
    public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getChurchName() {
		return churchName;
	}

	public String getChurchRoom() {
		return churchRoom;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}
	
	public LocalDateTime getOpen() {
		return open;
	}
	
	public LocalDateTime getOpenLimitAt() {
		return openLimitAt;
	}
	
	public LocalDateTime getCloseLimitAt() {
		return closeLimitAt;
	}
   
}
