package br.tads.ufpr.sgci_backend.esb.routes;

import br.tads.ufpr.sgci_backend.esb.DTO.ParticipantDTO;
import br.tads.ufpr.sgci_backend.esb.service.ParticipantRegisterService;
import br.tads.ufpr.sgci_backend.experiment.model.ParticipantEntity;
import br.tads.ufpr.sgci_backend.experiment.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/participant")
public class EsbParticipantRoutesController {
    private final ParticipantService participantService;
    private final ParticipantRegisterService participantRegisterService;

    @Autowired
    public EsbParticipantRoutesController(ParticipantService participantService, ParticipantRegisterService participantRegisterService) {
        this.participantService = participantService;
        this.participantRegisterService = participantRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody ParticipantDTO participantDTO) {
        try{
            ParticipantEntity participant = participantRegisterService.registerParticipant(participantDTO);
            participantService.createParticipant(participant);
            return new ResponseEntity<>("User created successfully", HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<>("Error creating user", HttpStatus.BAD_REQUEST);
        }
    }
}
