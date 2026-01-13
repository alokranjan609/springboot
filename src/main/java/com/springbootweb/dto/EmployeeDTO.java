package com.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Required field is :name")
    private String name;
    @Email(message ="Incorrect format of email" )
    private String email;
    @PastOrPresent(message = "Invalid date of joining")
    private LocalDate dateofjoining;
    @JsonProperty("isActive")
    private Boolean isActive;
}
