package com.example.lab7.Model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class Course {
    private String id;
    private String name;
    @Range(min = 1, max = 8)
    private int hours;
    private Instructor instructor;
}
