package com.myproject.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoDto {
    @NotEmpty
    private Long id;
    @Size(min=1, message = "title should have atleast 1 char")
    private String title;
    private String description;

//    @Email
//    private String email;
}
