package br.tads.ufpr.sgci_backend.researcher.service;

import br.tads.ufpr.sgci_backend.researcher.model.Researcher;

import java.util.List;

public interface UserService {
    Researcher createUser(Researcher researcher);
    Researcher getUserById(Long id);
    List<Researcher> getAllUsers();
    Researcher updateUser(Long id, Researcher researcher);
    void deleteUser(Long id);
}