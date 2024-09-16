package br.tads.ufpr.sgci_backend.researcher.repository;

import br.tads.ufpr.sgci_backend.researcher.model.ResearcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("researcherRepository")
public interface ResearcherRepository extends JpaRepository<ResearcherEntity, Long> {
    ResearcherEntity findByEmail(String email);
    List<ResearcherEntity> findByLastname(String lastName);

}