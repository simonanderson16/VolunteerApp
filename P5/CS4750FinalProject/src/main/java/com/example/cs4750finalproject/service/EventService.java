package com.example.cs4750finalproject.service;

import com.example.cs4750finalproject.model.Event;
import com.example.cs4750finalproject.model.Organization;
import com.example.cs4750finalproject.repository.EventRepository;
import com.example.cs4750finalproject.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByOrganization(Integer orgId) {
        return eventRepository.findByOrganizationOrgId(orgId);
    }

    public Event createEvent(Event event) {
        Organization organization = organizationRepository.findById(event.getOrganization().getOrgId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        event.setOrganization(organization);

        return eventRepository.save(event);
    }

    public Event updateEvent(Integer id, Event updatedEvent) {
        Optional<Event> existingEventOptional = eventRepository.findById(id);
        if (existingEventOptional.isEmpty()) {
            throw new RuntimeException("Event not found");
        }

        Event existingEvent = existingEventOptional.get();
        existingEvent.setCapacity(updatedEvent.getCapacity());
        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setLocation(updatedEvent.getLocation());

        Organization organization = organizationRepository.findById(updatedEvent.getOrganization().getOrgId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        existingEvent.setOrganization(organization);

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}