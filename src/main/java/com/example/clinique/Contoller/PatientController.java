package com.example.clinique.Contoller;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clinique.entity.Patient;
import com.example.clinique.repository.PatientRepository;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {


@Autowired
private PatientRepository patientRepository;



@GetMapping
public List<Patient> getAllPatients() {
    return patientRepository.findAll();
}

@GetMapping("/{id}")
public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
    Optional<Patient> optionalPatient = patientRepository.findById(id); //Optional :means that it can contain value or be vide
   
    return optionalPatient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));   //it display ok if there is value , not _found err otherwise
}
/* mn8ir response enity
@GetMapping("/{id}")
  public Patient getPatientById(@PathVariable Long id) {
    Optional<Patient> optionalPatient = patientRepository.findById(id);
    return optionalPatient.orElse(null); // Return the patient if present, or null if not found
 }
}
*/
@PostMapping
public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
    Patient createdPatient = patientRepository.save(patient);
    return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
    if (!patientRepository.existsById(id)) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    patient.setIdpatient(id);
    Patient updatedPatient = patientRepository.save(patient);
    return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
    if (!patientRepository.existsById(id)) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    patientRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
/*@GetMapping("/{id}")  //blech response entity
public void deletePatient(@PathVariable Long id) {
    if (!patientRepository.existsById(id)) {
        // If the patient doesn't exist, return without doing anything
        return;
    }
    // If the patient exists, delete it
    patientRepository.deleteById(id);
} */
}
