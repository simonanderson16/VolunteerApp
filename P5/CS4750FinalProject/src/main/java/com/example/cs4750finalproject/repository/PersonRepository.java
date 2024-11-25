package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
}

