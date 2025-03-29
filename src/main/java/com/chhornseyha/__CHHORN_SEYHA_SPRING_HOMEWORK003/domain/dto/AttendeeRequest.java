package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AttendeeRequest {
    @NotBlank(message = "AttendeeName can't be Blank !!")
    @NotNull(message = "AttendeeName can't be null !!")
    private String attendeeName;
    @NotBlank(message = "Email can't be Blank !!")
    @NotNull(message = "Email can't be null !!")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    @Schema(defaultValue = "example@gmail.com")
    private String email;
}
