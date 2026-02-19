package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.Classroom;

public interface ClassroomService {

     //falta valorar relaciones, sobretodo en el create

    public Classroom createClassroom (int idTeacher, Classroom classroom);

    public List<Classroom> getAllClassrooms();

    public Classroom getClassroomById (int id);

    public void updateClassroom (int id, Classroom classroomUpdated);

    public void deleteArtClassroom (int id);
    
    public void addPointsToClassroom(int id, int points);
}
