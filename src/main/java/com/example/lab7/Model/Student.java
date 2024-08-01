package com.example.lab7.Model;

import jakarta.validation.constraints.AssertFalse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private int hours;
    @AssertFalse(message = "this value must be false at initialization")
    private boolean isGraduated;
    private String major;
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
