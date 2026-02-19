package com.hackaton.waste.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.entity.DiscardedWaste;
import com.hackaton.waste.repository.ClassroomRepository;
import com.hackaton.waste.repository.DiscardedWasteRepository;

import jakarta.transaction.Transactional;

@Service
public class DiscardedWasteServiceImpl implements DiscardedWasteService {

    private DiscardedWasteRepository discardedRepository;
    private ClassroomService classroomService;


    public DiscardedWasteServiceImpl(DiscardedWasteRepository discardedRepository,
            ClassroomService classroomService ) {
        this.discardedRepository = discardedRepository;
        this.classroomService = classroomService;
    }

    @Transactional
    public DiscardedWaste processWaste(DiscardedWaste record) {

        boolean isCorrect = record.getResidue().getWasteType()
                .equals(record.getContainer().getWasteType());
        
        int points = isCorrect ? 10 : -5;
        if (record.getContainer().getWasteType().getName().equalsIgnoreCase("Resto")) {
            points = -2; 
        }

        record.setIsCorrect(isCorrect);
        record.setPointsEarned(points);
        record.setDate(LocalDateTime.now());

        Classroom classroom = record.getClassroom();
        classroom.setPoints(classroom.getPoints() + points);
        classroomService.addPointsToClassroom(record.getClassroom().getId(), points);

        return discardedRepository.save(record);
    }
}

