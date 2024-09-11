package br.tads.ufpr.sgci_backend.esb.routes;

import br.tads.ufpr.sgci_backend.authentication.model.Role;
import br.tads.ufpr.sgci_backend.authentication.model.UserEntity;
import br.tads.ufpr.sgci_backend.authentication.repository.RoleRepository;
import br.tads.ufpr.sgci_backend.authentication.repository.UserRepository;
import br.tads.ufpr.sgci_backend.authentication.service.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class esbAuthRoutesController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public esbAuthRoutesController(AuthenticationManager authenticationManager,
                                   PasswordEncoder passwordEncoder,
                                   RoleRepository roleRepository,
                                   UserRepository userRepository,
                                   JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
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

    public record RegisterDTO(String username, String password) {}

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterDTO register){
        if (register.username == null || register.password == null){
            return new ResponseEntity<>("User Cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUsername(register.username)){
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(register.username);
        user.setPassword(passwordEncoder.encode(register.password));
        if (roleRepository.findByName("USUARIO").isEmpty()){
            return new ResponseEntity<>("Role Usuario does not exists!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Role roles = roleRepository.findByName("USUARIO").get();
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);

        return new ResponseEntity<>("User created successfully", HttpStatus.OK);
    }
}
