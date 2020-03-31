package br.org.congregacao.locals.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.azam.ulidj.ULID;

@Document(collection = "workgroup")
public class WorkGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    private String id = ULID.random();
    @Indexed(unique = true)
    private String name;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    public WorkGroup() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private WorkGroup(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public static WorkGroup of(final String name, final String description) {
        return new WorkGroup(name, description);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkGroup other = (WorkGroup) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
    
}
