package com.hackaton.waste.service;

import com.hackaton.waste.entity.Classroom;

public class ClassroomServiceImpl {
    
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
