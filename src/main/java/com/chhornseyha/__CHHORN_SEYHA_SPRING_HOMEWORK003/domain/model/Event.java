package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    private Integer eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Venue venue;
    private List<Attendee> attendees;

}
