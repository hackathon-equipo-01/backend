package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.entity.User;
import com.hackaton.waste.entity.enums.Role;
import com.hackaton.waste.repository.ClassroomRepository;

import jakarta.transaction.Transactional;

public class ClassroomServiceImpl implements ClassroomService{
    private final ClassroomRepository classroomRepository;
    private final UserService userService;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, UserService userService) {
        this.classroomRepository = classroomRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Classroom createClassroom(int idTeacher, Classroom classroom) {
        
        User teacher = userService.getUserById(idTeacher);

        if (teacher.getRole() != Role.TEACHER) {
        throw new RuntimeException("El usuario debe ser un profesor para gestionar un aula.");
        }
        classroom.setTeacher(teacher);
        classroom.setPoints(0);

        return classroomRepository.save(classroom);
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom getClassroomById(int id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula no encontrada con ID: " + id));
    }
    @Override
    public void updateClassroom(int id, Classroom classroomUpdated) {
        Classroom classroom = this.getClassroomById(id);
        classroom.setCourse(classroomUpdated.getCourse());

        if (classroomUpdated.getTeacher() != null) {
            User newTeacher = userService.getUserById(classroomUpdated.getTeacher().getId());

            if (newTeacher.getRole() != Role.TEACHER) {
            throw new RuntimeException("Error: El usuario asignado no tiene rol de PROFESOR.");
            }
            classroom.setTeacher(newTeacher);
        }
        classroomRepository.save(classroom);
    }

    @Override
    public void deleteArtClassroom(int id) {
        classroomRepository.deleteById(id);
    }

@Override
public void addPointsToClassroom(int id, int points) {
    Classroom classroom = classroomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
    
    int newScore = classroom.getPoints() + points;
    
    // Aquí podrías añadir lógica de negocio extra:
    // Por ejemplo: si el puntaje es muy bajo, activar una alerta automática
    if (newScore < 0) { newScore = 0; } 
    
    classroom.setPoints(newScore);
    classroomRepository.save(classroom);
}
}
