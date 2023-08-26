package com.example.HW082123.service;

import com.example.HW082123.model.Faculty;
import com.example.HW082123.model.Student;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty (Faculty faculty);
    Faculty findFaculty (long id);
    Faculty editFaculty (long id, Faculty faculty);
    void deleteFaculty (long id);


    Collection<Faculty> getFilterByColor(String color);
    Faculty getFacultyById (Long id);


    Faculty editFaculty(Long id, Faculty faculty);
    Collection<Faculty> getAllFaculty();

    Collection<Student> findStudent(String name);


}
