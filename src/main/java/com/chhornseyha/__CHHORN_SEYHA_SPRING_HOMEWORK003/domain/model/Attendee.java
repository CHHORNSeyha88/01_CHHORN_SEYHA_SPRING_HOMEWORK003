package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
