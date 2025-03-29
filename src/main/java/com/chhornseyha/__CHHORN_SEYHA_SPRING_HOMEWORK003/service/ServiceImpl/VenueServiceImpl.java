package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.ServiceImpl;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.VenueRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Venue;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.exception.NotFoundException;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository.VenueRepository;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.Serviceinterface.VenueInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenueServiceImpl implements VenueInterface {
    private final VenueRepository venueRepository;
    @Override
    public Venue insertVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }

    @Override
    public Venue selectVenueById(Integer id) {
        Venue venue = venueRepository.selectVenueById(id);
        if(venue ==null){
            throw new NotFoundException("can't with this ID :" + id);
        }
        return venue;
    }

    @Override
    public Venue updateVenue(Integer id, VenueRequest venueRequest) {
        Venue venue = venueRepository.selectVenueById(id);
        if(venue ==null){
            throw new NotFoundException("can't with this ID :" + id);
        }
        return venueRepository.updateVenue(venueRequest,id);
    }

    @Override
    public void deleteVenue(int id) {
        Venue venue = venueRepository.selectVenueById(id);
        if(venue ==null){
            throw new NotFoundException("can't with this ID :" + id);
        }
        venueRepository.deleteVenueById(id);
    }


    @Override
    public List<Venue> getAllVenue(Integer size, Integer page) {
        return venueRepository.selectAllVenue(size,page);
    }
}
