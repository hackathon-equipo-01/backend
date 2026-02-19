package com.hackaton.waste.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.entity.Notification;
import com.hackaton.waste.repository.NotificationRepository;

import jakarta.transaction.Transactional;

public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public void sendAlertToClassroom(String message, Classroom classroom) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setClassroom(classroom);
        notification.setDate(LocalDateTime.now());
        notification.setRead(false); 

        notificationRepository.save(notification);
        
        System.out.println("ALERTA: " + message + " en el aula " + classroom.getCourse());
    }
    @Override
    @Transactional
    public void sendAlert(String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDate(LocalDateTime.now());
        notification.setRead(false); 

        notificationRepository.save(notification);
        
        System.out.println("ALERTA: " + message);
    }

    @Override
    public List<Notification> getNotificationsByClassroom(int classroomId) {
        return notificationRepository.findByClassroom_IdOrderByDateDesc(classroomId);
    }

    @Override
    @Transactional
    public void markAsRead(int id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con ID: " + id));
        n.setRead(true);
        notificationRepository.save(n);
    }

    @Override
    public long countUnreadNotifications(int classroomId) {
        return notificationRepository.countByClassroom_IdAndIsReadFalse(classroomId);
    }

    @Override
    @Transactional
    public void deleteNotification(int id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("No se puede borrar: Notificación no existe.");
        }
        notificationRepository.deleteById(id);
    }

}
