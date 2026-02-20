package com.hackaton.waste.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<?> processDiscard(@PathVariable int idClassroom, 
                                                         @PathVariable int idResidue, 
                                                         @PathVariable int idContainer) {
    try {
        DiscardedWaste result = discardedWasteService.processWaste(idClassroom, idResidue, idContainer);
        Map<String, Object> response = new HashMap<>();
        response.put("id", result.getId());
        response.put("isCorrect", result.getIsCorrect());
        response.put("pointsEarned", result.getPointsEarned());
        
       return ResponseEntity.ok(response); 
     } catch (Exception e) {
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
 }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<DiscardedWaste>> getHistoryByClassroom(@PathVariable int classroomId) {
        List<DiscardedWaste> history = discardedWasteService.getHistoryByClassroom(classroomId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping
    public List<DiscardedWaste> getAll() {
        return discardedWasteService.getAllDiscardedWastes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        discardedWasteService.deleteDiscardedWaste(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

