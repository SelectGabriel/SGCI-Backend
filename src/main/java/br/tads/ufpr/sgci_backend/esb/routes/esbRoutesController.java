package br.tads.ufpr.sgci_backend.esb.routes;

import br.tads.ufpr.sgci_backend.authentication.model.Role;
import br.tads.ufpr.sgci_backend.authentication.model.UserEntity;
import br.tads.ufpr.sgci_backend.authentication.repository.RoleRepository;
import br.tads.ufpr.sgci_backend.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class esbRoutesController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public esbRoutesController(AuthenticationManager authenticationManager,
                               PasswordEncoder passwordEncoder,
                               RoleRepository roleRepository,
                               UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public record RegisterDTO(String username, String password) {}

    @PostMapping("/register")
    public ResponseEntity<String> register (RegisterDTO register){
        if (userRepository.existsByUsername(register.username)){
            return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(register.username);
        user.setPassword(passwordEncoder.encode(register.password));

        Role roles = roleRepository.findByName("Usuario").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return null;
    }
}
