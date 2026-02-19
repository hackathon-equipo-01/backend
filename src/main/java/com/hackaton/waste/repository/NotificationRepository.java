package com.hackaton.waste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.waste.entity.Notification;

public interface NotificationRepository extends JpaRepository <Notification, Integer>{

    List<Notification> findByClassroom_IdOrderByDateDesc(int classroomId);
    
    long countByClassroom_IdAndIsReadFalse(int classroomId);
}
