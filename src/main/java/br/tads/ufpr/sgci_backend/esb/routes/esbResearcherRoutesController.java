package br.tads.ufpr.sgci_backend.esb.routes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/researchers")
public class esbResearcherRoutesController {

    @GetMapping("/hello")
    public ResponseEntity<String> helloResearcher(HttpServletRequest request){
        return new ResponseEntity<>("Hellooo!!", HttpStatus.OK);
    }
}
