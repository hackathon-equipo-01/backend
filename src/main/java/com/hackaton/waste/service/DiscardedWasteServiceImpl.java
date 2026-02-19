package com.hackaton.waste.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.entity.Container;
import com.hackaton.waste.entity.DiscardedWaste;
import com.hackaton.waste.entity.Residue;
import com.hackaton.waste.entity.enums.State;
import com.hackaton.waste.repository.DiscardedWasteRepository;

import jakarta.transaction.Transactional;

@Service
public class DiscardedWasteServiceImpl implements DiscardedWasteService {

    private DiscardedWasteRepository discardedRepository;
    private ResidueService residueService;
    private ContainerService containerService;
    private ClassroomService classroomService;
    private NotificationService notificationService;

    public DiscardedWasteServiceImpl(DiscardedWasteRepository discardedRepository, 
            NotificationService notificationService, ResidueService residueService,
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

        int newVolume = container.getCurrentVolume() + residue.getSize();
            container.setCurrentVolume(newVolume);
            updateContainerState(container);

            containerService.updateContainer(container.getId(),container);

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

                Container c = dw.getContainer();
                c.setCurrentVolume(Math.max(0, c.getCurrentVolume() - dw.getResidue().getSize()));
                updateContainerState(c);

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

    private void updateContainerState(Container container) {
    double porcentaje = (double) container.getCurrentVolume() / container.getMaxCapacity();

    if (porcentaje >= 1.0) {
        container.setState(State.FULL);
        notificationService.sendAlert(
            "¡Atención! El contenedor de " + container.getWasteType().getName() + " está lleno.");
    } else if (porcentaje >= 0.7) {
        container.setState(State.HALF_FULL);
    } else if (porcentaje > 0) {
        container.setState(State.HALF_EMPTY);
    } else {
        container.setState(State.EMPTY);
    }
}

}

