package com.hackaton.waste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.service.ClassroomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable int id) {
        Classroom classroom = classroomService.getClassroomById(id);
        return new ResponseEntity<>(classroom, HttpStatus.OK);
    }

    @PostMapping("/teacher/{idTeacher}")
    public ResponseEntity<Classroom> createClassroom(@PathVariable int idTeacher, @Valid @RequestBody Classroom classroom) {
        Classroom newClassroom = classroomService.createClassroom(idTeacher, classroom);
        return new ResponseEntity<>(newClassroom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateClassroom(@PathVariable int id, @Valid @RequestBody Classroom classroomUpdated) {
        classroomService.updateClassroom(id, classroomUpdated);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClassroom(@PathVariable int id) {
        classroomService.deleteArtClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/points")
    public ResponseEntity<HttpStatus> addPoints(@PathVariable int id, @RequestBody int points) {
        classroomService.addPointsToClassroom(id, points);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

