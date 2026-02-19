package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.Residue;

public interface ResidueService {

    //falta valorar relaciones, sobretodo en el create

    public Residue createResidue (int idWasteType, Residue residue);

    public List<Residue> getAllResidues();

    public Residue getResidueById (int id);

    public void updateResidue (int id, Residue residueUpdated);

    public void deleteResidue (int id);
}
