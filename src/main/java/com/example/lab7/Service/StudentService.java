package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import com.example.lab7.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<Student>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(Student student, String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public boolean addCourse(Course course, String sId) {
        for (Student student : students) {
            if (student.getId().equals(sId)) {
                student.addCourse(course);
                return true;
            }
        }
        return false;
    }

    public boolean removeCourse(String sId, String cId) {
        for (Student student : students) {
            if (student.getId().equals(sId)) {
                return student.removeCourse(cId);
            }
        }
        return false;
    }

    public boolean graduate(String id){
        for (Student student : students) {
            if (student.getId().equals(id)){
                student.setGraduated(true);
                return true;
            }
        }
        return false;
    }

    public Student getStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}
