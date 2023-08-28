package com.example.HW082123.controller;


import com.example.HW082123.model.Faculty;
import com.example.HW082123.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class StudentControllerRestTemplateIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoad() {
    }

    @Test
    public void testGetStudentInfo() {
        ResponseEntity<Student> newStudentResponse=
                testRestTemplate.postForEntity("http://localhost:"+port+"/student",new Student(1L,"Boris",20),Student.class );
        Assertions.assertThat(newStudentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Student newStudent = newStudentResponse.getBody();
        ResponseEntity<Student> studentEntity=
                testRestTemplate.getForEntity("http://localhost:"+port+"/student/"+newStudent.getId(), Student.class);
        Assertions.assertThat(studentEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(studentEntity.getBody()).isNotNull();

        Student student = studentEntity.getBody();
        Assertions.assertThat(student.getId()).isEqualTo(newStudent.getId());
        Assertions.assertThat(student.getName()).isEqualTo(newStudent.getName());
        Assertions.assertThat(student.getAge()).isEqualTo(newStudent.getAge());
    }
}