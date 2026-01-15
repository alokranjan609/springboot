package com.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
    @NotBlank(message="Required Field is:title")
    private String title;
    @JsonProperty("isActive")
    private Boolean isActive;
    @PastOrPresent(message="Invalid date,it must be of past or present")
    private LocalDate createdAt;


}
