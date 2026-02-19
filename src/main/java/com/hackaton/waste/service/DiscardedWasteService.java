package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.DiscardedWaste;

public interface DiscardedWasteService {

     //falta valorar relaciones, sobretodo en el create

    public DiscardedWaste createDiscardedWaste (int idClassroom, int idResidue, int idContainer, DiscardedWaste dw);

    DiscardedWaste processWaste (DiscardedWaste record);

    public List<DiscardedWaste> getAllDiscardedWastes();

    public DiscardedWaste getDiscardedWasteById (int id);

    public void updateDiscardedWaste (int id, DiscardedWaste dwUpdated);

    public void deleteArtDiscardedWaste (int id);
}
