package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty
    @Pattern(regexp = "(\\d){2}")
    private String id;
    @NotEmpty
    @Size(min = 4, max = 9)
    private String name;
    @NotEmpty
    @Min(1)
    @Max(140)
    private int hours;
    @AssertFalse(message = "this value must be false at initialization")
    private boolean isGraduated;
    @Pattern(regexp = "(CS|IT|IS)")
    private String major;
    @NotNull
    private final ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }
    public boolean removeCourse(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                courses.remove(course);
                return true;
            }
        }
        return false;
    }
}
