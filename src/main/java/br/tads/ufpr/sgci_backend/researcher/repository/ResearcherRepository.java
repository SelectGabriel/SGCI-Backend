package br.tads.ufpr.sgci_backend.researcher.repository;

import br.tads.ufpr.sgci_backend.researcher.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("researcherRepository")
public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
    Researcher findByEmail(String email);
    List<Researcher> findByLastName(String lastName);

}