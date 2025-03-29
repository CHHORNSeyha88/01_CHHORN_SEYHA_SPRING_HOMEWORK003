package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.Serviceinterface;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.AttendeeRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.EventRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Attendee;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Event;

import java.util.List;

public interface EventInterface {
    Event insertEvent(EventRequest eventRequest);
    Event selectEventById(Integer id);
    Event updateEvent(Integer id,EventRequest eventRequest);
    void deleteEvent(int id);
    List<Event> getAllEvents(Integer size, Integer page);
}
