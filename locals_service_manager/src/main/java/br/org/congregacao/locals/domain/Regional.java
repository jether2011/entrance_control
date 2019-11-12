package br.org.congregacao.locals.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.azam.ulidj.ULID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "regionals")
public final class Regional implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    private String id = ULID.random();
    @Indexed(unique = true)
    private String initial;
    private String description;

    @Indexed
    private Set<Administration> administrations = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    public Regional() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private Regional(final String initial, final String description) {
        this.initial = initial;
        this.description = description;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public static Regional of(final String initial, final String description) {
        return new Regional(initial, description);
    }

    public String getId() {
        return id;
    }

    public String getInitial() {
        return initial;
    }

    public String getDescription() {
        return description;
    }

    public Set<Administration> getAdministrations() {
        return Collections.unmodifiableSet(administrations);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void addAdministration(final Administration administration) {
    	this.updated = LocalDateTime.now();
        this.administrations.add(administration);
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, initial);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regional other = (Regional) obj;
		return Objects.equals(id, other.id) && Objects.equals(initial, other.initial);
	}
    
}
