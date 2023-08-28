package com.example.HW082123.controller;

import com.example.HW082123.model.Faculty;
import com.example.HW082123.model.Student;
import com.example.HW082123.service.StudentServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;
    @SpyBean
    private StudentServiceImpl studentService;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postStudentControllerTest() throws Exception{
        Student student = new Student();
        student.setId(7L);
        student.setAge(20);
        student.setName("Boris");
        student.setFaculty();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student",student,Student.class))
                .isNotNull();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/facultyStudent",student,Student.class))
                .isNotNull();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/age",student,Student.class))
                .isNotNull();

    }
}
