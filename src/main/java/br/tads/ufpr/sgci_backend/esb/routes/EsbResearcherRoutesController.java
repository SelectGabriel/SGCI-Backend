package br.tads.ufpr.sgci_backend.esb.routes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/researchers")
public class EsbResearcherRoutesController {

    @GetMapping("/hello")
    public ResponseEntity<String> helloResearcher(){
        return new ResponseEntity<>("Hellooo!!", HttpStatus.OK);
    }
}
