package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.ServiceImpl;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.EventRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Event;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.exception.NotFoundException;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository.EventRepository;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.Serviceinterface.EventInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventInterface {
    private final EventRepository eventRepository;


    @Override
    public Event insertEvent(EventRequest eventRequest) {
        Integer eventId = eventRepository.insertEvent(eventRequest);
        for(Integer attendeeId : eventRequest.getAttendeesId()){
            eventRepository.insertEventAttendee(eventId,attendeeId);
        }
        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event selectEventById(Integer id) {
        Event event = eventRepository.getEventById(id);
        if(event ==null){
            throw new NotFoundException("can't find with this ID :"+id);
        }
        return event;
    }

    @Override
    public Event updateEvent(Integer id, EventRequest eventRequest) {
        //        validation
        Integer updateRowsValidation = eventRepository.updateEvent(eventRequest, id);
        if(updateRowsValidation == 0){
            throw new RuntimeException("Student with ID " + id + " not found");
        }
        // handle new course
        List<Integer> newAttendeeUpdating = eventRequest.getAttendeesId();
        if(newAttendeeUpdating != null){
            eventRepository.deleteMiddleTable(id);
        }
        //បញ្ចូលថែមថ្មី
        for(Integer attendeeId : newAttendeeUpdating){
            eventRepository.insertEventAttendee(id, attendeeId);
        }
        return eventRepository.getEventById(id);

    }

    @Override
    public void deleteEvent(int id) {
        Event event = eventRepository.getEventById(id);
        if(event ==null){
            throw new NotFoundException("can't find with this ID :"+id);
        }
        eventRepository.deleteNormalEvent(id);

    }

    @Override
    public List<Event> getAllEvents(Integer size, Integer page) {
        return eventRepository.getAllEventWithPagination(size,page);
    }
}
