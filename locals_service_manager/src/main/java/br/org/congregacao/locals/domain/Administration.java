package br.org.congregacao.locals.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.azam.ulidj.ULID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "administrations")
public class Administration implements Serializable {

    @Id
    private String id = ULID.random();
    @Indexed(unique = true)
    private String name;
    @Indexed(unique = true)
    private String cnpj;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    public Administration() {
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    private Administration(final String name, final String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    public static Administration of(final String name, final String cnpj) {
        return new Administration(name, cnpj);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
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
}
