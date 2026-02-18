package com.hackaton.waste.service;

import java.util.List;

import com.hackaton.waste.entity.User;

public interface UserService {

//falta valorar relaciones, sobretodo en el create

    public User createUser (int idClassroom, User user);

    public User createTeacher (int idClassroomAsigned, User user);

    public List<User> getAllUsers();

    public User getUserById (int id);

    public void updateUser (int id, User userUpdated);

    public void deleteUser (int id);
}
