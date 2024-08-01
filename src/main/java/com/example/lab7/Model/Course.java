package com.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty
    @Pattern(regexp = "(\\d){2}")
    private String id;
    @NotEmpty
    @Size(min = 6, max = 15)
    private String name;
    @Range(min = 1, max = 8)
    @NotNull
    private int hours;
    @NotNull
    private Instructor instructor;
}
