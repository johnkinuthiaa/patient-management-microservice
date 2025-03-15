package com.slippery.patientservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class BaseEntity {
    private String message;
    private int statusCode;
}
