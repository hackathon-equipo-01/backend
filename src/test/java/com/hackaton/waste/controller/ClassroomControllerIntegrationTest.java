package com.hackaton.waste.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.hackaton.waste.entity.User;
import com.hackaton.waste.entity.enums.Role;
import com.hackaton.waste.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;


@SpringBootTest
@AutoConfigureMockMvc
class ClassroomControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final UserRepository userRepository;

    // En JUnit 5, el constructor del test NECESITA @Autowired 
    // para inyectar MockMvc
    @Autowired
    ClassroomControllerIntegrationTest(MockMvc mockMvc, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }

    @Test
    void shouldCreateNewClassroom() throws Exception {
        User teacher = new User();
        teacher.setName("Profe de Prueba");
        teacher.setEmail("profe@test.com");
        teacher.setPassword("1234");
        teacher.setRole(Role.TEACHER);
        // Guarda el profesor y obtén el ID generado
        User savedTeacher = userRepository.save(teacher); 
        Integer teacherId = savedTeacher.getId();
        String classroomJson = "{\"course\": \"2º Primaria\", \"letter\": \"A\"}";

        mockMvc.perform(post("/api/classrooms/teacher/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(classroomJson))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.course").value("2º Primaria"));
    }
}

