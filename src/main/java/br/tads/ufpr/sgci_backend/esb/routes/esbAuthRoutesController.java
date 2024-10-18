package br.tads.ufpr.sgci_backend.esb.routes;

import br.tads.ufpr.sgci_backend.authentication.service.JWTGenerator;
import br.tads.ufpr.sgci_backend.authentication.service.JwtBlacklistService;
import br.tads.ufpr.sgci_backend.authentication.service.SecurityConstants;
import br.tads.ufpr.sgci_backend.esb.DTO.RegisterDTO;
import br.tads.ufpr.sgci_backend.esb.orchestrator.UserResearcherRegisterOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class esbAuthRoutesController {
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final UserResearcherRegisterOrchestrator userResearcherRegisterOrchestrator;
    private final JwtBlacklistService jwtBlacklistService;

    @Autowired
    public esbAuthRoutesController(AuthenticationManager authenticationManager,
                                   JWTGenerator jwtGenerator, UserResearcherRegisterOrchestrator userResearcherRegisterOrchestrator, JwtBlacklistService jwtBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userResearcherRegisterOrchestrator = userResearcherRegisterOrchestrator;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    public record LoginDTO (String username, String password) {}
    public record AuthResponseDTO (String accessToken, String tokenType){};

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO login){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.username, login.password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token, "Bearer ");
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            jwtBlacklistService.blacklistToken(token, SecurityConstants.JWT_EXPIRATION);
            return new ResponseEntity<>("User logged out successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authorization header missing or invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterDTO register) {
        try{
            userResearcherRegisterOrchestrator.orchestrate(register);
            return new ResponseEntity<>("User created successfully", HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<>("Error creating user", HttpStatus.BAD_REQUEST);
        }
    }
}