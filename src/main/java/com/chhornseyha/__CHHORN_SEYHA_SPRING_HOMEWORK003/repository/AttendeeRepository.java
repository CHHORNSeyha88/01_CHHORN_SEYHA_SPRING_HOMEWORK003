package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.AttendeeRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.VenueRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Attendee;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    //-- find by Id
    @Select("""
    SELECT * FROM attendees WHERE attendee_id = #{attendeesId}
""")
    @Results(id = "attendeesMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    Attendee selectAttendeeById(@Param("attendeesId") Integer id);

    //    Attendee relationship with event
    @Select("""
    SELECT a.* 
    FROM attendees a
    INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
    WHERE ea.event_id = #{eventId}
""")
    @ResultMap("attendeesMapper")
    List<Attendee> selectAttendeeRelationshipWithEventById(@Param("eventId") Integer eventId);

    //-- select all
    @Select("""
      select * from attendees
      offset #{size}*(#{page}-1)
      limit #{size};
""")
    @ResultMap("attendeesMapper")
    List<Attendee> selectAllAttendee(Integer size, Integer page);

    //-- insertion
    @Select("""
                insert into attendees(attendee_name,email)
                VALUES(#{attendeeRequest.attendeeName}, #{attendeeRequest.email})
                returning *;

            """)
    @ResultMap("attendeesMapper")
    Attendee insertAttendee(@Param("attendeeRequest") AttendeeRequest attendeeRequest);

    @Select("""
    UPDATE attendees
    SET attendee_name = #{attendeeRequest.attendeeName}, email = #{attendeeRequest.email}
    WHERE attendee_id = #{updateId}
    RETURNING *
""")
    @ResultMap("attendeesMapper")
    Attendee updateAttendee(@Param("attendeeRequest") AttendeeRequest attendeeRequest, @Param("updateId") Integer updateId);

    //-- delete
    @Delete("DELETE FROM attendees WHERE attendee_id = #{attendeeId}")
    void deleteAttendeeById(@Param("attendeeId") Integer id); // No return value
}
