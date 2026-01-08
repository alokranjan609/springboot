package com.springbootweb.dto;

import java.time.LocalDate;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateofjoining;
    private Boolean isActive;

    public EmployeeDTO(){

    }

    public Long getId() {
        return id;
    }

    public EmployeeDTO(Long id, String name, String email, LocalDate dateofjoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateofjoining = dateofjoining;
        this.isActive = isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateofjoining() {
        return dateofjoining;
    }

    public void setDateofjoining(LocalDate dateofjoining) {
        this.dateofjoining = dateofjoining;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
