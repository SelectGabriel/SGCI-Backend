package br.tads.ufpr.sgci_backend.researcher.service;

import br.tads.ufpr.sgci_backend.researcher.model.Researcher;
import br.tads.ufpr.sgci_backend.researcher.repository.ResearcherRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ResearcherRepository researcherRepository;

    public UserServiceImpl(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
    }

    @Override
    public Researcher createUser(Researcher person) {
        return null;
    }

    @Override
    public Researcher getUserById(Long id) {
        return researcherRepository.findById(id).orElse(null);
    }

    @Override
    public List<Researcher> getAllUsers() {
        return researcherRepository.findAll();
    }

    @Override
    public Researcher updateUser(Long id, Researcher person) {
        Optional<Researcher> existingUser = researcherRepository.findById(id);
        if (existingUser.isPresent()) {
            Researcher updatedPerson = existingUser.get();
            updatedPerson.setName(person.getName());
            updatedPerson.setEmail(person.getEmail());
            // outras atualizações
            return researcherRepository.save(updatedPerson);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        researcherRepository.deleteById(id);
    }
}