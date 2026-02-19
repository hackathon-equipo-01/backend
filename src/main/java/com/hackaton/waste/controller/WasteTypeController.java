package com.hackaton.waste.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.waste.entity.WasteType;
import com.hackaton.waste.service.WasteTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/waste-types")
public class WasteTypeController {

    private WasteTypeService wasteTypeService;

    public WasteTypeController(WasteTypeService wasteTypeService) {
        this.wasteTypeService = wasteTypeService;
    }

    @GetMapping
    public ResponseEntity<List<WasteType>> getAll() {
        List<WasteType> types = wasteTypeService.getAllWasteTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteType> getById(@PathVariable int id) {
        WasteType wt = wasteTypeService.getWasteTypeById(id);
        return new ResponseEntity<>(wt, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WasteType> createWasteType(@Valid @RequestBody WasteType wt) {
        WasteType newType = wasteTypeService.createWasteType(wt);
        return new ResponseEntity<>(newType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateWasteType(@PathVariable int id, @Valid @RequestBody WasteType wt) {
        wasteTypeService.updateWasteType(id, wt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWasteType(@PathVariable int id) {
        wasteTypeService.deleteWasteType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
