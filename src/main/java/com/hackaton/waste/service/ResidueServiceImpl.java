package com.hackaton.waste.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hackaton.waste.entity.Residue;
import com.hackaton.waste.entity.WasteType;
import com.hackaton.waste.repository.ResidueRepository;

import jakarta.transaction.Transactional;

@Service
public class ResidueServiceImpl implements ResidueService {

    private final ResidueRepository residueRepository;
    private final WasteTypeService wasteTypeService;
    private DiscardedWasteService discardedWasteService;

    public ResidueServiceImpl(ResidueRepository residueRepository, DiscardedWasteService discardedWasteService, WasteTypeService wasteTypeService) {
        this.residueRepository = residueRepository;
        this.wasteTypeService = wasteTypeService;
        this.discardedWasteService = discardedWasteService;
    }

    @Override
    @Transactional
    public Residue createResidue(int idWasteType, Residue residue) {
        WasteType wasteType = wasteTypeService.getWasteTypeById(idWasteType);
        residue.setWasteType(wasteType);
        return residueRepository.save(residue);
    }

    @Override
    public List<Residue> getAllResidues() {
        return residueRepository.findAll();
    }

    @Override
    public Residue getResidueById(int id) {
        return residueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Residuo no encontrado: " + id));
    }

    @Override
    @Transactional
    public void updateResidue(int id, Residue residueUpdated) {
        Residue residue = this.getResidueById(id);
        
        residue.setName(residueUpdated.getName());
        residue.setSize(residueUpdated.getSize());
        
        if (residueUpdated.getWasteType() != null) {
            WasteType newType = wasteTypeService.getWasteTypeById(residueUpdated.getWasteType().getId());
            residue.setWasteType(newType);
        }
        residueRepository.save(residue);
    }

    @Override
    public void deleteResidue(int id) {
        Residue residue = this.getResidueById(id);

        long usageCount = discardedWasteService.countByResidueId(id);
        if (usageCount > 0) {
        throw new RuntimeException("No se puede eliminar: este residuo ya ha sido registrado en el historial de reciclaje.");
    }
        residueRepository.delete(residue);
    }

}

