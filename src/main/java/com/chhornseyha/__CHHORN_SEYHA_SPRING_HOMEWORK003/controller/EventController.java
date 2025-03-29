package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.controller;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.constant.MessageConstant;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.constant.httpresponse.ApiResponse;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.EventRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Event;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.ServiceImpl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/events")
@Validated
public class EventController {
    private final EventServiceImpl eventService;

    @GetMapping
    @Operation(summary = "Get All Events")
    public ApiResponse<List<Event>> getAllEventsWithPagination(@RequestParam(defaultValue = "10") @Min(value = 1, message = "size must be greater than 0") int size, @RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "page must be greater than 0") int page){
        List<Event> events = eventService.getAllEvents(size,page);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Event>>builder()
                        .message(MessageConstant.Event.GET_ALL_EVENTS_SUCCESSFULLY)
                        .payload(events)
                        .status(HttpStatus.OK)
                        .build()
        ).getBody();
    }
    @GetMapping("/{event-id}")
    @Operation(summary = "Get All Event By ID")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id")  @Min(value = 1, message = "Id must be greater than 0")int venueId){
        Event event = eventService.selectEventById(venueId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .message(MessageConstant.Event.GET_EVENTS_BY_ID_SUCCESSFULLY)
                        .payload(event)
                        .status(HttpStatus.OK)
                        .build()
        );

    }

    @PostMapping
    @Operation(summary = "Add New Event")
    public ResponseEntity<ApiResponse<Event>> addEvent(@RequestBody @Valid EventRequest eventRequest) {
        Event createdEvent = eventService.insertEvent(eventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Event>builder()
                        .message(MessageConstant.Event.CREATES_SUCCESSFULLY)
                        .payload(createdEvent)
                        .status(HttpStatus.CREATED)
                        .build()
        );
    }

    @PutMapping("/{event-id}")
    @Operation(summary = "Update New Event")
    public ResponseEntity<ApiResponse<Event>> updateEvent(@PathVariable("event-id")  @Min(value = 1, message = "Id must be greater than 0") int id, @RequestBody @Valid EventRequest eventRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .message(MessageConstant.Event.UPDATED_SUCCESSFULLY)
                        .payload(eventService.updateEvent(id, eventRequest))
                        .status(HttpStatus.OK)
                        .build()
        );

    }
    @DeleteMapping("/{event-id}")
    @Operation(summary = "Delete Event")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(
            @PathVariable("event-id") @Min(value = 1, message = "Id must be greater than 0") int id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .message(MessageConstant.Event.DELETED_SUCCESSFULLY)
                        .status(HttpStatus.OK)
                        .build()
        );

    }
}
