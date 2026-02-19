package com.hackaton.waste.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hackaton.waste.entity.Residue;
import com.hackaton.waste.service.ResidueService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/residues")
public class ResidueController {
    private final ResidueService residueService;

    public ResidueController(ResidueService residueService) {
        this.residueService = residueService;
    }

   @GetMapping
    public ResponseEntity<List<Residue>> getAllResidues() {
        List<Residue> residues = residueService.getAllResidues();
        return new ResponseEntity<>(residues, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Residue> getResidueById(@PathVariable int id) {
        Residue residue = residueService.getResidueById(id);
        return new ResponseEntity<>(residue, HttpStatus.OK);
    }

    @PostMapping("/waste-type/{idWasteType}")
    public ResponseEntity<Residue> createResidue(@PathVariable int idWasteType, @Valid @RequestBody Residue residue) {
        Residue newResidue = residueService.createResidue(idWasteType, residue);
        return new ResponseEntity<>(newResidue, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateResidue(@PathVariable int id, @Valid @RequestBody Residue residue) {
        residueService.updateResidue(id, residue);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteResidue(@PathVariable int id) {
        residueService.deleteResidue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}