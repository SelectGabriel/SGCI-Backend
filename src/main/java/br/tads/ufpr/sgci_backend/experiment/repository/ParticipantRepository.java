package br.tads.ufpr.sgci_backend.experiment.repository;

import br.tads.ufpr.sgci_backend.experiment.model.ParticipantEntity;
import br.tads.ufpr.sgci_backend.researcher.model.ResearcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("participantRepository")
public interface ParticipantRepository extends JpaRepository<ResearcherEntity, Long> {
    ParticipantEntity findByEmail(String email);
    List<ParticipantEntity> findByName(String lastName);
}