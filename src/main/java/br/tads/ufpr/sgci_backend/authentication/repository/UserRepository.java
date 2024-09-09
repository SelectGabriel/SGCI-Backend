package br.tads.ufpr.sgci_backend.authentication.repository;

import br.tads.ufpr.sgci_backend.authentication.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
