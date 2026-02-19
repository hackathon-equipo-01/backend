package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.Classroom;
import com.hackaton.waste.entity.Notification;

public interface NotificationService {

    public void sendAlert(String message);

    public void sendAlertToClassroom( String message, Classroom classroom);

    public List<Notification> getNotificationsByClassroom(int classroomId);

    public void markAsRead(int id);

    public long countUnreadNotifications(int classroomId);

    public void deleteNotification(int id);
}
