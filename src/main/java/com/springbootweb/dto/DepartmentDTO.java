package com.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DepartmentDTO {
    private Long Id;
    private String title;
    @JsonProperty("isActive")
    private Boolean isActive;
    private LocalDate createdAt;


}
