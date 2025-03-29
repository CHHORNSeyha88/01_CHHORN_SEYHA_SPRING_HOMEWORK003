package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.controller;

import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.constant.MessageConstant;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.constant.httpresponse.ApiResponse;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto.VenueRequest;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model.Venue;
import com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.service.ServiceImpl.VenueServiceImpl;
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
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueServiceImpl venueService;
    @GetMapping
    @Operation(summary = "Get All Venues")
    public ApiResponse<List<Venue>> getAllVenuesWithPagination(@RequestParam(defaultValue = "10") @Min(value = 1, message = "size must be greater than 0") int size, @RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "page must be greater than 0") int page){
        List<Venue> venues = venueService.getAllVenue(size,page);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Venue>>builder()
                        .message(MessageConstant.Venues.GET_ALL_VENUES_SUCCESSFULLY)
                        .payload(venues)
                        .status(HttpStatus.OK)
                        .build()
        ).getBody();
    }
    @GetMapping("/{venue-id}")
    @Operation(summary = "Get All Venues By ID")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id")  @Min(value = 1, message = "Id must be greater than 0")int venueId){
        Venue venue = venueService.selectVenueById(venueId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venue>builder()
                        .message(MessageConstant.Venues.GET_VENUES_BY_ID_SUCCESSFULLY)
                        .payload(venue)
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping
   @Operation(summary = "Add New Venue")
    public ResponseEntity<ApiResponse<Venue>> addVenue(@RequestBody @Valid VenueRequest venueRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Venue>builder()
                        .message(MessageConstant.Venues.CREATES_SUCCESSFULLY)
                        .payload(venueService.insertVenue(venueRequest))
                        .status(HttpStatus.CREATED)
                        .build()
        );

    }
    @PutMapping("/{venue-id}")
    @Operation(summary = "Update New Venue")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(@PathVariable("venue-id")  @Min(value = 1, message = "Id must be greater than 0") int id, @RequestBody @Valid VenueRequest venueRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venue>builder()
                        .message(MessageConstant.Venues.UPDATED_SUCCESSFULLY)
                        .payload(venueService.updateVenue(id, venueRequest))
                        .status(HttpStatus.OK)
                        .build()
        );

    }

    @DeleteMapping("/{venue-id}")
    @Operation(summary = "Delete Venue")
    public ResponseEntity<ApiResponse<Void>> deleteVenue(
            @PathVariable("venue-id") @Min(value = 1, message = "Id must be greater than 0") int id) {
        venueService.deleteVenue(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .message(MessageConstant.Venues.DELETED_SUCCESSFULLY)
                        .status(HttpStatus.OK)
                        .build()
        );
    }




}
