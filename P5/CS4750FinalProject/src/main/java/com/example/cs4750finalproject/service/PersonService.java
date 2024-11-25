package com.example.cs4750finalproject.service;

import com.example.cs4750finalproject.model.EmergencyContact;
import com.example.cs4750finalproject.model.Person;
import com.example.cs4750finalproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Integer id, Person updatedPerson) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setName(updatedPerson.getName());
            person.setEmail(updatedPerson.getEmail());
            person.setPassword(updatedPerson.getPassword());
            person.setEmergencyContact(updatedPerson.getEmergencyContact());
            return personRepository.save(person);
        } else {
            throw new RuntimeException("Person not found");
        }
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
