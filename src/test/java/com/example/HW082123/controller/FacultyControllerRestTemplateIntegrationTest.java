package com.example.HW082123.controller;

import com.example.HW082123.model.Faculty;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class FacultyControllerRestTemplateIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoad() {
    }
    @Test
    public void testGetFacultyInfo(){
        ResponseEntity<Faculty> newFacultyResponse =
                testRestTemplate.postForEntity("http://localhost:"+port+"/faculty", new Faculty(1L,"green","New Faculty"),Faculty.class);
        Assertions.assertThat(newFacultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);


        Faculty newFaculty =newFacultyResponse.getBody();
        ResponseEntity<Faculty> facultyEntity =
                testRestTemplate.getForEntity("http://localhost:"+port+"/faculty/"+newFaculty.getId(), Faculty.class);
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(facultyEntity.getBody()).isNotNull();

        Faculty faculty = facultyEntity.getBody();
        Assertions.assertThat(faculty.getId()).isEqualTo(newFaculty.getId());
        Assertions.assertThat(faculty.getName()).isEqualTo(newFaculty.getName());
        Assertions.assertThat(faculty.getColor()).isEqualTo(newFaculty.getColor());
    }


  /*  @Test
    public void testAllGetFaculties(){
        ResponseEntity<Faculty> newFacultyResponse =
                testRestTemplate.postForEntity("http://localhost:"+port+"/faculty", new Faculty(2L,"red","New Faculty"),Faculty.class);
        Assertions.assertThat(newFacultyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);



        ResponseEntity<List> facultyEntity =
                testRestTemplate.getForEntity("http://localhost:"+port+"/faculty/", List.class);
        Assertions.assertThat(facultyEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(facultyEntity.getBody()).isNotNull();


    }*/
}

