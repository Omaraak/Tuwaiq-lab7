package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Model.Student;
import com.example.lab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        if (studentService.getStudents().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No students found"));
        return ResponseEntity.status(200).body(studentService.getStudents());
    }
    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Student student, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ApiResponse("Student added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id, @Valid @RequestBody Student student, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (studentService.updateStudent(student, id))
            return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if (studentService.deleteStudent(id))
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }
    @PutMapping("/addCourse/{sId}")
    public ResponseEntity addCourse(@PathVariable String sId, @Valid @RequestBody Course course, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (studentService.addCourse(course, sId))
            return ResponseEntity.status(200).body(new ApiResponse("Course added"));
        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }
    @PutMapping("/removeCourse/{sId}/{cId}")
    public ResponseEntity removeCourse(@PathVariable String sId, @PathVariable String cId){
        if (studentService.removeCourse(sId,cId))
            return ResponseEntity.status(200).body(new ApiResponse("Course removed"));
        return ResponseEntity.status(404).body(new ApiResponse("Course not found or student not found"));
    }
    @PutMapping("/graduate/{id}")
    public ResponseEntity graduate(@PathVariable String id){
        if (studentService.graduate(id))
            return ResponseEntity.status(200).body(new ApiResponse("Student has Graduated"));
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable String id){
        Student student = studentService.getStudent(id);
        if (student == null)
            return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(student);
    }
}
