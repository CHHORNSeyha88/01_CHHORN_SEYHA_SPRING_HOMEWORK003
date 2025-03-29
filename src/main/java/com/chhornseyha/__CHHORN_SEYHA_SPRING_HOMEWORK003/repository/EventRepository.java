package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.EventRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Attendee;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
        SELECT * 
        FROM events
        INNER JOIN venues ON events.venue_id = venues.venue_id
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size};
    """)
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository.VenueRepository.selectVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository.AttendeeRepository.selectAttendeeRelationshipWithEventById"))
    })
    List<Event> getAllEventWithPagination(int size, int page);

    @Select("""
        SELECT * FROM events WHERE event_id = #{id};
    """)
    @ResultMap("eventMapper")
    Event getEventById(int id);

    @Insert("""
        INSERT INTO event_attendee(event_id, attendee_id)
        VALUES (#{eventId}, #{attendeeId})
    """)
    void insertEventAttendee(@Param("eventId") Integer eventId, @Param("attendeeId") Integer attendeeId);



    @Select("""
       INSERT INTO events(event_name, event_date, venue_id)\s
                           VALUES (#{eventRequest.eventName}, #{eventRequest.eventDate}, #{eventRequest.venueId})
                           RETURNING event_id;
   \s""")
    Integer insertEvent(@Param("eventRequest") EventRequest eventRequest);

    //    --update
    @Select("""
UPDATE events
SET event_name = #{eventRequest.eventName}, event_date = #{eventRequest.eventDate}, venue_id = #{eventRequest.venueId}
WHERE event_id = #{eventId}
returning event_id;

""")
    Integer updateEvent(@Param("eventRequest") EventRequest eventRequest, Integer eventId);

    //-- Delete all event as normal
    @Delete("""
        DELETE FROM events
        WHERE event_id = #{eventId};
    """)
    void deleteNormalEvent(@Param("eventId") Integer eventId);

    //-- Delete middle table
    @Delete("""
DELETE from event_attendee
where event_id = #{eventId}
""")
    void deleteMiddleTable(@Param("eventId") Integer eventId);
}