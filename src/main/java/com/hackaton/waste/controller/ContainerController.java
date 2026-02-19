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

import com.hackaton.waste.entity.Container;
import com.hackaton.waste.service.ContainerService;

@RestController
@RequestMapping("/api/containers")
public class ContainerController {

    private ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping
    public ResponseEntity<List<Container>> getAll() {
        List<Container> containers = containerService.getAllContainers();
        return new ResponseEntity<>(containers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Container> getById(@PathVariable int id) {
        Container container = containerService.getContainerById(id);
        return new ResponseEntity<>(container, HttpStatus.OK);
    }

    @PostMapping("/waste-type/{idWasteType}")
    public ResponseEntity<Container> create(@PathVariable int idWasteType, @RequestBody Container container) {
        Container newContainer = containerService.createContainer(idWasteType, container);
        return new ResponseEntity<>(newContainer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id, @RequestBody Container container) {
        containerService.updateContainer(id, container);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        containerService.deleteContainer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
