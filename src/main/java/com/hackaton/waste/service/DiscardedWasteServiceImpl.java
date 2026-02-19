package com.hackaton.waste.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.entity.Container;
import com.hackaton.waste.entity.DiscardedWaste;
import com.hackaton.waste.entity.Residue;
import com.hackaton.waste.repository.DiscardedWasteRepository;

import jakarta.transaction.Transactional;

@Service
public class DiscardedWasteServiceImpl implements DiscardedWasteService {

    private DiscardedWasteRepository discardedRepository;
    private ResidueService residueService;
    private ContainerService containerService;
    private ClassroomService classroomService;

    public DiscardedWasteServiceImpl(DiscardedWasteRepository discardedRepository, ResidueService residueService,
            ContainerService containerService, ClassroomService classroomService) {
        this.discardedRepository = discardedRepository;
        this.residueService = residueService;
        this.containerService = containerService;
        this.classroomService = classroomService;
    }

    @Override
    @Transactional
    public DiscardedWaste processWaste(int idClassroom, int idResidue, int idContainer) {

        Classroom classroom = classroomService.getClassroomById(idClassroom);
        Residue residue = residueService.getResidueById(idResidue);
        Container container = containerService.getContainerById(idContainer);

        
        boolean isCorrect = Objects.equals(residue.getWasteType().getId(), container.getWasteType().getId());
        
        int points = isCorrect ? 10 : -5;
        if (container.getWasteType().getName().equalsIgnoreCase("Resto")) {
            points = points -2; 
        }

        DiscardedWaste dw = new DiscardedWaste();
        dw.setClassroom(classroom);
        dw.setResidue(residue);
        dw.setContainer(container);
        dw.setIsCorrect(isCorrect);
        dw.setPointsEarned(points);
        dw.setDate(LocalDateTime.now());

        classroomService.addPointsToClassroom(idClassroom, points);

        return discardedRepository.save(dw);
    }

    @Override
    public List<DiscardedWaste> getHistoryByClassroom(int classroomId) {
        return discardedRepository.findByClassroomIdOrderByDateDesc(classroomId);
    }
    @Override
    public List<DiscardedWaste> getAllDiscardedWastes(){
        return discardedRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteDiscardedWaste(int id) {
        DiscardedWaste dw = discardedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        classroomService.addPointsToClassroom(dw.getClassroom().getId(), -dw.getPointsEarned());

        discardedRepository.delete(dw);
    }

    @Override
    public DiscardedWaste getDiscardedWasteById(int id) {
        return discardedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("objeto no encontrado con ID: " + id));
    }

    @Override
    public long countByResidueId(int id){
        return discardedRepository.countByResidueId(id);
    } 

     @Override
    public long countByContainerId(int id){
        return discardedRepository.countByContainerId(id);
    }

}

