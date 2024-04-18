package com.example.clinique.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinique.entity.Medecin;
import com.example.clinique.repository.MedecinRepository;

@RestController
@RequestMapping("/api/medecins")



public class MedecinController {

@Autowired
private MedecinRepository MedecinRepository;


@GetMapping
public List<medecin> getAllMedecins() {
    return MedecinRepository.findAll();
}

@GetMapping("/{id}")
  public Medecin getPatientById(@PathVariable Long id) {
    Optional<Medecin> medecin = MedecinRepository.findById(id);
    return medecin.orElse(null); 
 }
}

@PostMapping
public ResponseEntity<Medecin> createMedecin(@RequestBody Medecin Medecin) {
    Medecin createdMedecin = MedecinRepository.save(Medecin);
    return new ResponseEntity<>(createdMedecin, HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
    if (!MedecinRepository.existsById(id)) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medecin.setIdmedecin(id);
    medecin updateMedecin = MedecinRepository.save(medecin);
    return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
}
}
