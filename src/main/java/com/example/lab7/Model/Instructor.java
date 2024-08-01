package com.example.lab7.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Instructor {
    private String id;
    private String name;
    private int numberOfPublishedPapers;
    private String position;
    private ArrayList<Course> coursesTeached = new ArrayList<>();
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
