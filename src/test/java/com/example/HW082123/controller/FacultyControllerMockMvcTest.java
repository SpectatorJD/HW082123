package com.example.HW082123.controller;

import com.example.HW082123.model.Faculty;
import com.example.HW082123.repository.FacultyRepository;
import com.example.HW082123.service.FacultyService;
import com.example.HW082123.service.FacultyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private FacultyServiceImpl facultyService;
    @MockBean
    private FacultyRepository facultyRepository;

    @Test
    public void testGetFacultyInfo() throws Exception {
        when(facultyRepository.findAll()).thenReturn(List.of(
                new Faculty(1L, "green", "New Faculty"),
                new Faculty(2L, "black", "New Faculty1")
        ));
        /*mockMvc.perform(MockMvcRequestBuilders.get("/faculty"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("New Faculty"))
                .andExpect(jsonPath("$[0].color").value("green"));
        verify(facultyRepository, times(1)).findAll();
        verify(facultyService, times(1)).getAllFaculty();
    }*/
    }
}
