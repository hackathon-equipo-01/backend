package com.hackaton.waste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.entity.Container;
import com.hackaton.waste.entity.DiscardedWaste;
import com.hackaton.waste.entity.Residue;
import com.hackaton.waste.entity.WasteType;
import com.hackaton.waste.repository.DiscardedWasteRepository;


@ExtendWith(MockitoExtension.class)
public class DiscardedWasteServiceImplTest {

    @Mock
    private DiscardedWasteRepository repository;
    @Mock
    private ResidueService residueService;
    @Mock
    private ContainerService containerService;
    @Mock
    private ClassroomService classroomService;
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private DiscardedWasteServiceImpl discardedWasteService;

    @Test
    void testProcessWaste_CorrectRecycling_AddsTenPoints() {
        
        int classroomId = 1;
        int residueId = 10;
        int containerId = 100;
        // 1. Preparar el WasteType
        WasteType plastic = new WasteType("Plástico");
        plastic.setId(1);

        Residue residue = new Residue();
        residue.setId(residueId);
        residue.setWasteType(plastic);
        residue.setSize(5);

        Container container = new Container();
        container.setId(containerId);
        container.setWasteType(plastic); // Mismo tipo para que sea "Correcto"
        container.setMaxCapacity(100);
        container.setCurrentVolume(0);

        Classroom classroom = new Classroom();
        classroom.setId(classroomId);

        when(residueService.getResidueById(residueId)).thenReturn(residue);
        when(containerService.getContainerById(containerId)).thenReturn(container);
        when(classroomService.getClassroomById(classroomId)).thenReturn(classroom);

        when(repository.save(any(DiscardedWaste.class))).thenAnswer(i -> i.getArguments()[0]);
        DiscardedWaste result = discardedWasteService.processWaste(classroomId, residueId, containerId);

        assertNotNull(result);
        assertTrue(result.getIsCorrect(), "El reciclaje debería ser correcto");
        assertEquals(10, result.getPointsEarned());
        verify(classroomService).addPointsToClassroom(1, 10);
    }
}

