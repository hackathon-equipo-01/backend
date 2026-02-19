package com.hackaton.waste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.waste.entity.DiscardedWaste;
import com.hackaton.waste.service.DiscardedWasteService;

@RestController
@RequestMapping("/api/discarded-waste")
public class DiscardedWasteController {

    private final DiscardedWasteService discardedWasteService;

    public DiscardedWasteController(DiscardedWasteService discardedWasteService) {
        this.discardedWasteService = discardedWasteService;
    }

    @PostMapping ("/classroom/{idClassroom}/residue/{idResidue}/container/{idContainer}")
    public ResponseEntity<DiscardedWaste> processDiscard(@PathVariable int idClassroom, 
                                                         @PathVariable int idResidue, 
                                                         @PathVariable int idContainer) {

        DiscardedWaste result = discardedWasteService.processWaste(idClassroom, idResidue, idContainer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<DiscardedWaste>> getHistoryByClassroom(@PathVariable int classroomId) {
        List<DiscardedWaste> history = discardedWasteService.getHistoryByClassroom(classroomId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        discardedWasteService.deleteDiscardedWaste(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

