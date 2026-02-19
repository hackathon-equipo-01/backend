package com.hackaton.waste.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hackaton.waste.entity.User;
import com.hackaton.waste.entity.enums.Role;
import com.hackaton.waste.repository.UserRepository;


    @Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     @Override
    public User createTeacher(User user) {
        user.setRole(Role.TEACHER);
       return userRepository.save(user);
    }
    
    @Override
    public User createUser(User user) {
         user.setRole(Role.USER);
       return userRepository.save(user);
    }

    @Override
    public List<User> getAllTeachers() {
        return userRepository.findByRole(Role.TEACHER);
    }

     @Override
    public User getUserById(int id) {
      return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

     @Override
    public void updateUser(int id, User userUpdated) {
        User user = this.getUserById(id);
    
        user.setName(userUpdated.getName());
        user.setEmail(userUpdated.getEmail());
        user.setPassword(userUpdated.getPassword());
    
        if (userUpdated.getClassroom() != null) {
        user.setClassroom(userUpdated.getClassroom());
        }
    
        userRepository.save(user);
    }

    public User updateTeacher(int id, User teacherUpdated){
        User user = this.getUserById(id);

        if (user.getRole() != Role.TEACHER) {
        throw new RuntimeException("El usuario no es un profesor profesor");
        }
        user.setRole(teacherUpdated.getRole() != null ? teacherUpdated.getRole() : user.getRole());
        user.setName(teacherUpdated.getName());
        user.setEmail(teacherUpdated.getEmail());
        user.setPassword(teacherUpdated.getPassword());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
