package com.example.cs4750finalproject.service;

import com.example.cs4750finalproject.model.EmergencyContact;
import com.example.cs4750finalproject.model.Person;
import com.example.cs4750finalproject.repository.EmergencyContactRepository;
import com.example.cs4750finalproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        EmergencyContact contact = emergencyContactRepository.findById(person.getEmergencyContact().getContactId())
                .orElseThrow(() -> new RuntimeException("EmergencyContact not found"));

        person.setEmergencyContact(contact);

        return personRepository.save(person);
    }

    public Person updatePerson(Integer id, Person updatedPerson) {
        Optional<Person> existingPersonOptional = personRepository.findById(id);
        if (existingPersonOptional.isEmpty()) {
            throw new RuntimeException("Person not found");
        }
        Person existingPerson = existingPersonOptional.get();
        existingPerson.setName(updatedPerson.getName());
        existingPerson.setEmail(updatedPerson.getEmail());
        existingPerson.setPassword(updatedPerson.getPassword());
        EmergencyContact contact = emergencyContactRepository.findById(updatedPerson.getEmergencyContact().getContactId())
                .orElseThrow(() -> new RuntimeException("EmergencyContact not found"));
        existingPerson.setEmergencyContact(contact);
        return personRepository.save(existingPerson);
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
