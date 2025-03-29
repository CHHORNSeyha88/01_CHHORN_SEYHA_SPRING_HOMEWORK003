package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventRequest {
    @NotNull(message = "Event Name not Null !!")
    @NotBlank(message = "Event Name not Blank !!")
    private String eventName;

    @NotNull(message = "Event Date not Null !!")
    private LocalDateTime eventDate;

    @Schema(defaultValue = "0")
    @Positive(message = "must greater than 0")
    @Min(value = 1,message = "must greater than 0")
    private Integer venueId;

    @Schema(defaultValue = "[0]")
    @NotNull(message = "Attendees list cannot be null")
    private List<@Min(value = 1, message = "Attendee ID must be greater than 0") Integer> attendeesId;}
