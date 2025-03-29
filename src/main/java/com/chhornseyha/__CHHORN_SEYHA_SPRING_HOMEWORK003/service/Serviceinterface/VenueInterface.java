package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.Serviceinterface;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.VenueRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Venue;

import java.util.List;

public interface VenueInterface {

    Venue insertVenue(VenueRequest venueRequest);
    Venue selectVenueById(Integer id);
    Venue updateVenue(Integer id,VenueRequest venueRequest);
    void deleteVenue(int id);
    List<Venue> getAllVenue(Integer size, Integer page);
}
