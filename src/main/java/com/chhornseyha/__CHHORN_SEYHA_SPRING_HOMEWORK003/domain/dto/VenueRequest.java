package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VenueRequest {

    @NotBlank(message = "VenueName can't be Blank !!")
    @NotNull(message = "VenueName can't be null !!")
    private String venueName;
    @NotBlank(message = "Location can't be Blank !!")
    @NotNull(message = "Location can't be null !!")
    private String location;
}
