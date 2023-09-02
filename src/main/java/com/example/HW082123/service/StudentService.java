package com.example.HW082123.service;

import com.example.HW082123.model.Faculty;
import com.example.HW082123.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent (Student student);
    Student findStudent (long id);

    Collection<Student> getByAge(Integer min, Integer max);

    Student editStudent (long id, Student student);
    void deleteStudent (long id);

    Faculty findFaculty(String name);
}