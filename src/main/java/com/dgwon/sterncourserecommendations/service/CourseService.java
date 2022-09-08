package com.dgwon.sterncourserecommendations.service;

import com.dgwon.sterncourserecommendations.domain.Course;
import com.dgwon.sterncourserecommendations.domain.Department;
import com.dgwon.sterncourserecommendations.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Create
    public Course createCourse(String courseCode, String title, Department department) {
        return this.courseRepository.findById(courseCode)
                .orElse(this.courseRepository.save(new Course(courseCode, title, department)));
    }

    // Read
    public Iterable<Course> lookup() {
        return this.courseRepository.findAll();
    }

    // Update

    // Delete
}
