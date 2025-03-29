package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.Serviceinterface;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.AttendeeRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Attendee;

import java.util.List;

public interface AttendeeInterface {
    Attendee insertAttendee(AttendeeRequest attendeeRequest);
    Attendee selectAttendeeById(Integer id);
    Attendee updateAttendee(Integer id,AttendeeRequest attendeeRequest);
    void deleteAttendee(int id);
    List<Attendee> getAllAttendee(Integer size, Integer page);
}
