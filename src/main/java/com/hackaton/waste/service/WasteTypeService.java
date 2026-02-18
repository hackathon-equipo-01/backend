package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.WasteType;

public interface WasteTypeService {

//falta valorar relaciones, sobretodo en el create

    public WasteType createWasteType (WasteType wt);

    public List<WasteType> getAllWasteTypes();

    public WasteType getWasteTypeById (int id);

    public void updateWasteType (int id, WasteType wtUpdated);

    public void deleteWasteType (int id);

}
