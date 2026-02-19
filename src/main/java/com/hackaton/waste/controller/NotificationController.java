package com.hackaton.waste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.waste.entity.Notification;
import com.hackaton.waste.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Notification>> getByClassroom(@PathVariable int classroomId) {
        List<Notification> notifications = notificationService.getNotificationsByClassroom(classroomId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    // Marcar una notificación como leída
    @PatchMapping("/{id}/read")
    public ResponseEntity<HttpStatus> markAsRead(@PathVariable int id) {
        notificationService.markAsRead(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Borrar notificaciones antiguas
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

