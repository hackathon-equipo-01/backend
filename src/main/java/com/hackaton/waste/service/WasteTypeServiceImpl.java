package com.hackaton.waste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hackaton.waste.entity.WasteType;
import com.hackaton.waste.repository.WasteTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class WasteTypeServiceImpl implements WasteTypeService{

    private WasteTypeRepository wtRepository;

    public WasteTypeServiceImpl (WasteTypeRepository wtRepository){
        this.wtRepository = wtRepository;
    }

    @Override
    public WasteType createWasteType(WasteType wt) {
        if (wtRepository.existsByName(wt.getName())) {
            throw new RuntimeException("Ese tipo de residuo ya existe");
            }   
       return wtRepository.save(wt);
    }

    @Override
    public List<WasteType> getAllWasteTypes() {
        return wtRepository.findAll();
    }

    @Override
    public WasteType getWasteTypeById(int id) {
        Optional<WasteType> optionalWt = wtRepository.findById(id);
        if(optionalWt.isEmpty()) throw new RuntimeException("No existe un tipo de residuo con ese id.");
        return optionalWt.get();
    }

    @Override
    public void updateWasteType(int id, WasteType wtUpdated) {
        WasteType wt = this.getWasteTypeById(id);

        if (wtUpdated.getName() != null && !wtUpdated.getName().isBlank()){
        wt.setName(wtUpdated.getName());
        }
        wtRepository.save(wt);
    }

    @Override
    @Transactional
    public void deleteWasteType(int id) {
        WasteType wt = this.getWasteTypeById(id);

        if (!wt.getResidueList().isEmpty() || !wt.getContainers().isEmpty()) {
            throw new RuntimeException("No se puede eliminar: existen residuos o contenedores vinculados a esta categoría.");
        }
        
        wtRepository.delete(wt);
    }
}
