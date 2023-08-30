package com.example.HW082123.controller;

import com.example.HW082123.model.Faculty;
import com.example.HW082123.model.Student;
import com.example.HW082123.repository.FacultyRepository;
import com.example.HW082123.repository.StudentRepository;
import com.example.HW082123.service.StudentServiceImpl;

import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes=Student.class)


public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private int port;
    @SpyBean
    private StudentServiceImpl studentService;
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private StudentRepository studentRepository;
    private final String DELETE_STUDENT_ENDPOINT_URL = "http://localhost:"+port+"/student//{id}";
    @Test
    public void postStudentControllerTest() throws Exception {
        Student student = new Student();
        student.setId(7L);
        student.setAge(20);
        student.setName("Boris");
        student.setFaculty();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
                .isNotNull();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/facultyStudent", student, Student.class))
                .isNotNull();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/age", student, Student.class))
                .isNotNull();
    }


    @Test
    public void testGetByAge() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age", Student.class))
                .isNotNull();
    }

    @Test
    public void testFindByName() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/facultyStudent", Student.class))
                .isNotNull();
    }
    @Test
    public void testEditStudent() throws Exception {
        Student student = new Student();
        student.setId(19L);
        student.setName("Bob");
        student.setAge(22);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
                .isNotNull();
    }

    @Test
    private void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setId(15L);
        student.setName("Ron");
        student.setAge(42);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(DELETE_STUDENT_ENDPOINT_URL, student);
    }
}
