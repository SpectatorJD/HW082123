package com.example.HW082123.controller;

import com.example.HW082123.model.Faculty;
import com.example.HW082123.model.Student;
import com.example.HW082123.service.FacultyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {


    @LocalServerPort
    private int port;
    @SpyBean
    private FacultyServiceImpl facultyService;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyService).isNotNull();
    }
    @Test
    public void postFacultyControllerTest() throws Exception{
        Faculty faculty = new Faculty();
        faculty.setId(5L);
        faculty.setName("Had");
        faculty.setColor("blue");
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty",faculty, Faculty.class))
                .isNotNull();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty",faculty, Faculty.class))
                .isNotNull();
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/color",faculty, Faculty.class))
                .isNotNull();

    }
    @Test
    public void getNameStudentFacultyTest() throws Exception{
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", Student.class))
                .isNotNull();
    }
}
