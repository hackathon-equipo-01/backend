package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.User;

public interface UserService {

//falta valorar relaciones, sobretodo en el create

    public User createUser (User user);

    public User createTeacher ( User user);

    public List<User> getAllTeachers();

    public User getUserById (int id);

    public void updateUser (int id, User userUpdated);

    public User updateTeacher (int id, User teacherUpdated);

    public void deleteUser (int id);
}
