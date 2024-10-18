package br.tads.ufpr.sgci_backend.experiment.service;

import br.tads.ufpr.sgci_backend.experiment.model.ParticipantEntity;
import br.tads.ufpr.sgci_backend.experiment.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ParticipantEntity createParticipant(ParticipantEntity participant) {
        participantRepository.save(participant);
        return participant;
    }

    public ParticipantEntity getParticipantById(Long id) {
        return participantRepository.findById(id).orElse(null);
    }

    public List<ParticipantEntity> getAllParticipants() {
        return participantRepository.findAll();
    }

    public ParticipantEntity updateParticipant(Long id, ParticipantEntity participant) {
        Optional<ParticipantEntity> existingParticipant = participantRepository.findById(id);
        if (existingParticipant.isPresent()) {
            ParticipantEntity updatedParticipant = existingParticipant.get();
            updatedParticipant.setName(participant.getName());
            updatedParticipant.setEmail(participant.getEmail());
            return participantRepository.save(updatedParticipant);
        }
        return null;
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}
