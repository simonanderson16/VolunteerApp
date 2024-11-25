package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.EmergencyContact;
import com.example.cs4750finalproject.repository.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmergencyContactController {

    @Autowired
    EmergencyContactRepository emergencyContactRepository;

    @GetMapping("/emergency-contacts")
    public ResponseEntity<List<EmergencyContact>> getAllEmergencyContacts(@RequestParam(required = false) String name) {
        try {
            List<EmergencyContact> contacts = new ArrayList<>();

            if (name == null)
                emergencyContactRepository.findAll().forEach(contacts::add);
            else
                emergencyContactRepository.findByNameContaining(name).forEach(contacts::add);

            if (contacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emergency-contacts/{id}")
    public ResponseEntity<EmergencyContact> getEmergencyContactById(@PathVariable("id") int id) {
        Optional<EmergencyContact> contactData = emergencyContactRepository.findById(id);

        if (contactData.isPresent()) {
            return new ResponseEntity<>(contactData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/emergency-contacts")
    public ResponseEntity<EmergencyContact> createEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
        try {
            EmergencyContact _contact = emergencyContactRepository
                    .save(new EmergencyContact(emergencyContact.getName(), emergencyContact.getPhoneNumber()));
            return new ResponseEntity<>(_contact, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/emergency-contacts/{id}")
    public ResponseEntity<EmergencyContact> updateEmergencyContact(@PathVariable("id") int id, @RequestBody EmergencyContact emergencyContact) {
        Optional<EmergencyContact> contactData = emergencyContactRepository.findById(id);

        if (contactData.isPresent()) {
            EmergencyContact _contact = contactData.get();
            _contact.setName(emergencyContact.getName());
            _contact.setPhoneNumber(emergencyContact.getPhoneNumber());
            return new ResponseEntity<>(emergencyContactRepository.save(_contact), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/emergency-contacts/{id}")
    public ResponseEntity<HttpStatus> deleteEmergencyContact(@PathVariable("id") int id) {
        try {
            emergencyContactRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/emergency-contacts")
    public ResponseEntity<HttpStatus> deleteAllEmergencyContacts() {
        try {
            emergencyContactRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
