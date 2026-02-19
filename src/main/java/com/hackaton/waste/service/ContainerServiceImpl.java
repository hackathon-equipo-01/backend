package com.hackaton.waste.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.hackaton.waste.entity.Container;
import com.hackaton.waste.entity.WasteType;
import com.hackaton.waste.entity.enums.State;
import com.hackaton.waste.repository.ContainerRepository;

import jakarta.transaction.Transactional;

@Service
public class ContainerServiceImpl implements ContainerService {

    private ContainerRepository containerRepository;
    private WasteTypeService wasteTypeService;
    private DiscardedWasteService discardedWasteService;

    public ContainerServiceImpl(ContainerRepository containerRepository, @Lazy DiscardedWasteService discardedWasteService, WasteTypeService wasteTypeService) {
        this.containerRepository = containerRepository;
        this.wasteTypeService = wasteTypeService;
        this.discardedWasteService = discardedWasteService;
    }

    @Override
    @Transactional
    public Container createContainer(int idWasteType, Container container) {
        WasteType type = wasteTypeService.getWasteTypeById(idWasteType);
        container.setWasteType(type);
        
        if (container.getState() == null) {
            container.setState(State.EMPTY); 
        }
        
        return containerRepository.save(container);
    }

    @Override
    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    @Override
    public Container getContainerById(int id) {
        return containerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public void updateContainer(int id, Container containerUpdated) {
        Container container = this.getContainerById(id);
        
        
        container.setState(containerUpdated.getState());
        
        if (containerUpdated.getWasteType() != null) {
            WasteType type = wasteTypeService.getWasteTypeById(containerUpdated.getWasteType().getId());
            container.setWasteType(type);
        }
        
        containerRepository.save(container);
    }

    @Override
    @Transactional
    public void deleteContainer(int id) {
        Container container = this.getContainerById(id);
        long usageCount = discardedWasteService.countByContainerId(id);

        if (usageCount > 0) {
        throw new RuntimeException("No se puede eliminar el contenedor: tiene " + usageCount + " registros de residuos asociados.");
        }
        containerRepository.delete(container);
    }

    @Override
    @Transactional
    public void emptyContainer(int id) {
        Container container = getContainerById(id);
        container.setCurrentVolume(0);
        container.setState(State.EMPTY);
        containerRepository.save(container);
    }
}
