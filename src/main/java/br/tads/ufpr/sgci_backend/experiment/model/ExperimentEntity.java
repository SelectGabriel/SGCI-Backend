package br.tads.ufpr.sgci_backend.experiment.model;

import br.tads.ufpr.sgci_backend.researcher.model.ResearcherEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_experimentos")
public class ExperimentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Relacionamento com Researcher (muitos experimentos podem ter o mesmo pesquisador)
    @ManyToOne
    @JoinColumn(name = "researcher_id", nullable = false) // Foreign Key para o Researcher
    private ResearcherEntity researcher;

    // Relacionamento com Participant (muitos experimentos podem ter o mesmo participante)
    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false) // Foreign Key para o Participant
    private ParticipantEntity participant;

    @Column(name = "experiment_star_date")
    private LocalDateTime experimentStartDate;
    private String observations;

    // Relacionamento com WalkEntity (um experimento pode ter várias caminhadas)
    @OneToMany(mappedBy = "experiment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WalkEntity> walks;

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResearcherEntity getResearcher() {
        return researcher;
    }

    public void setResearcher(ResearcherEntity researcher) {
        this.researcher = researcher;
    }

    public ParticipantEntity getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantEntity participant) {
        this.participant = participant;
    }

    public LocalDateTime getExperimentStartDate() {
        return experimentStartDate;
    }

    public void setExperimentStartDate(LocalDateTime experimentStartDate) {
        this.experimentStartDate = experimentStartDate;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public List<WalkEntity> getWalks() {
        return walks;
    }

    public void setWalks(List<WalkEntity> walks) {
        this.walks = walks;
    }
}
