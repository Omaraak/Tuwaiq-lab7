package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import com.example.lab7.Model.Instructor;
import com.example.lab7.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstructorService {
    ArrayList<Instructor> instructors = new ArrayList<Instructor>();

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public boolean updateInstructor(Instructor instructor, String id) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId().equals(id)) {
                instructors.set(i, instructor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteInstructor(String id) {
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(id)) {
                instructors.remove(instructor);
                return true;
            }
        }
        return false;
    }

    public Course getCourseById(String instructorId, String courseId) {
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(instructorId)) {
                return instructor.getCourse(courseId);
            }
        }
        return null;
    }

    public boolean published(String id){
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(id)) {
                instructor.setNumberOfPublishedPapers(instructor.getNumberOfPublishedPapers() + 1);
                return true;
            }
        }
        return false;
    }

    public boolean raise(String id){
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(id)) {
                instructor.setSalary(instructor.getSalary() * 1.2);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Instructor> getByPublishedPapers(int publishedPapers) {
        ArrayList<Instructor> instructorsByPublishedPapers = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getNumberOfPublishedPapers() <= publishedPapers)
                instructorsByPublishedPapers.add(instructor);
        }
        return instructorsByPublishedPapers;
    }

    public ArrayList<Instructor> getByCourse(String courseId) {
        ArrayList<Instructor> instructorsByCourse = new ArrayList<>();
        for (Instructor instructor : instructors) {
            for (Course course: instructor.getCoursesTeached()){
                if (course.getId().equals(courseId)){
                    instructorsByCourse.add(instructor);
                }
            }
        }
        return instructorsByCourse;
    }

    public boolean addCourse(Course course, String instructorId) {
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(instructorId)) {
                instructor.addCourse(course);
                return true;
            }
        }
        return false;
    }
}
