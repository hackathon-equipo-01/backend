package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.Container;

public interface ContainerService {

     //falta valorar relaciones, sobretodo en el create

    public Container createContainer (int idWasteType, Container container);

    public List<Container> getAllContainers();

    public Container getContainerById (int id);

    public void updateContainer (int id, Container containerUpdated);

    public void deleteContainer (int id);

    public void emptyContainer(int id);
}
