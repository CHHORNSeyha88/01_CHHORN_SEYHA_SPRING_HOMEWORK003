package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.ServiceImpl;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.AttendeeRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Attendee;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Venue;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.exception.NotFoundException;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.repository.AttendeeRepository;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.Serviceinterface.AttendeeInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeServiceImpl implements AttendeeInterface {
    private final AttendeeRepository attendeeRepository;

    @Override
    public Attendee insertAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    @Override
    public Attendee selectAttendeeById(Integer id) {
        Attendee attendee = attendeeRepository.selectAttendeeById(id);
        if(attendee==null){
            throw new NotFoundException("can't found with this Id: " +id);
        }
        return attendee;
    }

    @Override
    public Attendee updateAttendee(Integer id, AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeRepository.selectAttendeeById(id);
        if(attendee==null){
            throw new NotFoundException("can't found with this Id: " +id);
        }
        return attendeeRepository.updateAttendee(attendeeRequest,id);
    }

    @Override
    public void deleteAttendee(int id) {
        Attendee attendee = attendeeRepository.selectAttendeeById(id);
        if(attendee==null){
            throw new NotFoundException("can't found with this Id: " +id);
        }
        attendeeRepository.deleteAttendeeById(id);
    }

    @Override
    public List<Attendee> getAllAttendee(Integer size, Integer page) {
        return attendeeRepository.selectAllAttendee(size,page);
    }
}
