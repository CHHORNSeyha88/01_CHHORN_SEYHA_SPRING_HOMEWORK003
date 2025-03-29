package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.controller;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.constant.MessageConstant;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.constant.httpresponse.ApiResponse;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.AttendeeRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.VenueRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Attendee;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Venue;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.ServiceImpl.AttendeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeServiceImpl attendeeService;

    @GetMapping
    @Operation(summary = "Get All Attendees")
    public ApiResponse<List<Attendee>> getAllVenuesWithPagination(@RequestParam(defaultValue = "10") @Min(value = 1, message = "size must be greater than 0") int size, @RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "page must be greater than 0") int page){
        List<Attendee> attendee = attendeeService.getAllAttendee(size,page);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Attendee>>builder()
                        .message(MessageConstant.Attendees.GET_ALL_ATTENDEES_SUCCESSFULLY)
                        .payload(attendee)
                        .status(HttpStatus.OK)
                        .build()
        ).getBody();
    }
    @GetMapping("/{attendees-id}")
    @Operation(summary = "Get All Attendees By ID")
    public ResponseEntity<ApiResponse<Attendee>> getVenueById(@PathVariable("attendees-id")  @Min(value = 1, message = "Id must be greater than 0")int venueId){
        Attendee attendee = attendeeService.selectAttendeeById(venueId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .message(MessageConstant.Attendees.GET_ATTENDEES_BY_ID_SUCCESSFULLY)
                        .payload(attendee)
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping
    @Operation(summary = "Add New Attendee")
    public ResponseEntity<ApiResponse<Attendee>> addVenue(@RequestBody @Valid AttendeeRequest attendeeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Attendee>builder()
                        .message(MessageConstant.Attendees.CREATES_SUCCESSFULLY)
                        .payload(attendeeService.insertAttendee(attendeeRequest))
                        .status(HttpStatus.CREATED)
                        .build()
        );

    }

    @PutMapping("/{attendees-id}")
    @Operation(summary = "Update New Attendees")
    public ResponseEntity<ApiResponse<Attendee>> updateVenue(@PathVariable("attendees-id")  @Min(value = 1, message = "Id must be greater than 0") int id, @RequestBody @Valid AttendeeRequest attendeeRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .message(MessageConstant.Attendees.UPDATED_SUCCESSFULLY)
                        .payload(attendeeService.updateAttendee(id, attendeeRequest))
                        .status(HttpStatus.OK)
                        .build()
        );

    }

    @DeleteMapping("/{attendees-id}")
    @Operation(summary = "Delete Attendees")
    public ResponseEntity<ApiResponse<Void>> deleteVenue(
            @PathVariable("attendees-id") @Min(value = 1, message = "Id must be greater than 0") int id) {
        attendeeService.deleteAttendee(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .message(MessageConstant.Attendees.DELETED_SUCCESSFULLY)
                        .status(HttpStatus.OK)
                        .build()
        );
    }
    }
