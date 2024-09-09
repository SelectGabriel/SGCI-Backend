package br.tads.ufpr.sgci_backend.researcher.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_pesquisadores")
public class Researcher extends Person implements Serializable {
    private ResearcherType type;

    public Researcher() {
        super();
    }

    public Researcher(long id, String name, String lastName, String phone, String email, String document, ResearcherType type) {
        super(id, name, lastName, phone, email, document);
        this.type = type;
    }

    public Researcher(ResearcherType type) {
        this.type = type;
    }

    public ResearcherType getType() {
        return type;
    }

    public void setType(ResearcherType type) {
        this.type = type;
    }
}
