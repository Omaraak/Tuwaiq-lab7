package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Instructor {
    @NotEmpty
    @Pattern(regexp = "(\\d){2}")
    private String id;
    @NotEmpty
    @Size(min = 3, max = 10)
    private String name;
    @NotNull
    @Positive
    private int numberOfPublishedPapers;
    @Pattern(regexp = "([AB])")
    private String position;
    @NotNull
    private ArrayList<Course> coursesTeached = new ArrayList<>();
    @NotNull
    @Positive
    private double salary;

    public Instructor(String id, String name, int numberOfPublishedPapers, String position, double salary) {
        this.id = id;
        this.name = name;
        this.numberOfPublishedPapers = numberOfPublishedPapers;
        this.position = position;
        this.salary = salary;
    }
    public Course getCourse(String courseId) {
        for (Course course : coursesTeached) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
    public void addCourse(Course course) {
        coursesTeached.add(course);
    }
}
