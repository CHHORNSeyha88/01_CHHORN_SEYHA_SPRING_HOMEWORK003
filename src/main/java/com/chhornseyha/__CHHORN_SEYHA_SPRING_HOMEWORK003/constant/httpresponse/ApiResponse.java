package com.chhornseyha.__CHHORN_SEYHA_SPRING_HOMEWORK003.constant.httpresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    private String message;
    private T payload;
    private HttpStatus status;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
