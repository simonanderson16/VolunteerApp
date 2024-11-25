package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.BelongsTo;
import com.example.cs4750finalproject.service.BelongsToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class BelongsToController {

    @Autowired
    private BelongsToService belongsToService;

    @GetMapping
    public ResponseEntity<List<BelongsTo>> getMemberships(
            @RequestParam(required = false) Integer personId,
            @RequestParam(required = false) Integer orgId) {
        if (personId != null) {
            return new ResponseEntity<>(belongsToService.getMembershipsByPerson(personId), HttpStatus.OK);
        } else if (orgId != null) {
            return new ResponseEntity<>(belongsToService.getMembershipsByOrganization(orgId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(belongsToService.getAllMemberships(), HttpStatus.OK);
        }
    }


    @GetMapping("/person/{personId}")
    public ResponseEntity<List<BelongsTo>> getMembershipsByPerson(@PathVariable Integer personId) {
        return new ResponseEntity<>(belongsToService.getMembershipsByPerson(personId), HttpStatus.OK);
    }

    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<BelongsTo>> getMembershipsByOrganization(@PathVariable Integer orgId) {
        return new ResponseEntity<>(belongsToService.getMembershipsByOrganization(orgId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BelongsTo> addMembership(
            @RequestParam Integer personId,
            @RequestParam Integer orgId,
            @RequestParam String role) {
        return new ResponseEntity<>(belongsToService.addMembership(personId, orgId, role), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> removeMembership(
            @RequestParam Integer personId,
            @RequestParam Integer orgId) {
        belongsToService.removeMembership(personId, orgId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<BelongsTo> updateMembership(
            @RequestParam Integer personId,
            @RequestParam Integer orgId,
            @RequestParam String role) {
        try {
            BelongsTo updatedMembership = belongsToService.updateMembership(personId, orgId, role);
            return new ResponseEntity<>(updatedMembership, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
