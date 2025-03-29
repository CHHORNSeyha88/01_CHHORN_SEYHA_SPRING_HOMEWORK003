package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.VenueRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    //-- find by Id
    @Select("""
    SELECT * FROM venues WHERE venue_id = #{venueId}
""")
    @Results(id = "VenueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    Venue selectVenueById(@Param("venueId") Integer id);

    //-- select all
    @Select("""
      select * from venues
      offset #{size}*(#{page}-1)
      limit #{size};
""")
    @ResultMap("VenueMapper")
    List<Venue> selectAllVenue(Integer size, Integer page);

    //-- insertion
    @Select("""
                insert into venues(venue_name,location)
                VALUES(#{venueRequest.venueName}, #{venueRequest.location})
                returning *;

            """)
    @ResultMap("VenueMapper")
    Venue insertVenue(@Param("venueRequest") VenueRequest venueRequest);

    @Select("""
    UPDATE venues
    SET venue_name = #{venueRequest.venueName}, location = #{venueRequest.location}
    WHERE venue_id = #{updateId}
    RETURNING *
""")
    @ResultMap("VenueMapper")
    Venue updateVenue(@Param("venueRequest") VenueRequest venueRequest, @Param("updateId") Integer updateId);

    //-- delete
    @Delete("DELETE FROM venues WHERE venue_id = #{venueId}")
    void deleteVenueById(@Param("venueId") Integer id); // No return value
}
