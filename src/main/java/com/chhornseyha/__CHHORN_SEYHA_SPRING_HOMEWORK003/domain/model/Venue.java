package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venue {
    private Integer venueId;
    private String venueName;
    private String location;
}
