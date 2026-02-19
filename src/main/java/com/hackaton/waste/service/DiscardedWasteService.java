package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.DiscardedWaste;

public interface DiscardedWasteService {

     //falta valorar relaciones, sobretodo en el create

    public DiscardedWaste processWaste (int idClassroom, int idResidue, int idContainer);

    public List<DiscardedWaste> getAllDiscardedWastes();

    public List<DiscardedWaste> getHistoryByClassroom(int classroomId);

    public DiscardedWaste getDiscardedWasteById (int id);

    public void deleteDiscardedWaste (int id);
}
