package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Model.Instructor;
import com.example.lab7.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        if (instructorService.getInstructors().isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("There are no instructors."));
        return ResponseEntity.status(200).body(instructorService.getInstructors());
    }
    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Instructor instructor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(200).body(instructor);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id, @Valid @RequestBody Instructor instructor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (instructorService.updateInstructor(instructor,id))
            return ResponseEntity.status(200).body(new ApiResponse("Successfully updated instructor"));
        return ResponseEntity.status(400).body(new ApiResponse("Failed to update instructor"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if (instructorService.deleteInstructor(id))
            return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted instructor"));
        return ResponseEntity.status(400).body(new ApiResponse("Failed to delete instructor"));
    }
    @GetMapping("/getCourseById/{instructorId}/{courseId}")
    public ResponseEntity getCourseById(@PathVariable String instructorId, @PathVariable String courseId){
        Course course = instructorService.getCourseById(instructorId,courseId);
        if (course == null)
            return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
        return ResponseEntity.status(200).body(course);
    }
    @PutMapping("/published/{id}")
    public ResponseEntity published(@PathVariable String id){
        if (instructorService.published(id))
            return ResponseEntity.status(200).body(new ApiResponse("instructor successfully published a paper"));
        return ResponseEntity.status(400).body(new ApiResponse("instructor failed to publish a paper"));
    }
    @PutMapping("/raise/{id}")
    public ResponseEntity raise(@PathVariable String id){
        if (instructorService.raise(id))
            return ResponseEntity.status(200).body(new ApiResponse("instructor salary is raised by 20%"));
        return ResponseEntity.status(404).body(new ApiResponse("instructor not found"));
    }
    @GetMapping("/getByPublishedPapers/{publishedPapers}")
    public ResponseEntity getByPublishedPapers(@PathVariable int publishedPapers){
        if (instructorService.getByPublishedPapers(publishedPapers).isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No instructors found"));
        return ResponseEntity.status(200).body(instructorService.getInstructors());
    }
    @GetMapping("/getByCourse/{courseId}")
    public ResponseEntity getByCourse(@PathVariable String courseId){
        if (instructorService.getByCourse(courseId).isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No instructors found"));
        return ResponseEntity.status(200).body(instructorService.getInstructors());
    }
    @PutMapping("/addCourse/{id}")
    public ResponseEntity addCourse(@PathVariable String id, @Valid @RequestBody Course course, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (instructorService.addCourse(course, id))
            return ResponseEntity.status(200).body(new ApiResponse("Course added"));
        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }

}
