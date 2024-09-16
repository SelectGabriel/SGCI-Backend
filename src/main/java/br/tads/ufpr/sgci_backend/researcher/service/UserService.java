package br.tads.ufpr.sgci_backend.researcher.service;

import br.tads.ufpr.sgci_backend.researcher.model.ResearcherEntity;
import br.tads.ufpr.sgci_backend.researcher.repository.ResearcherRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final ResearcherRepository researcherRepository;

    public UserService(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
    }
    public ResearcherEntity createUser(ResearcherEntity person) {
        return null;
    }
    public ResearcherEntity getUserById(Long id) {
        return researcherRepository.findById(id).orElse(null);
    }
    public List<ResearcherEntity> getAllUsers() {
        return researcherRepository.findAll();
    }

    public ResearcherEntity updateUser(Long id, ResearcherEntity person) {
        Optional<ResearcherEntity> existingUser = researcherRepository.findById(id);
        if (existingUser.isPresent()) {
            ResearcherEntity updatedPerson = existingUser.get();
            updatedPerson.setName(person.getName());
            updatedPerson.setEmail(person.getEmail());
            return researcherRepository.save(updatedPerson);
        }
        return null;
    }

    public void deleteUser(Long id) {
        researcherRepository.deleteById(id);
    }
}