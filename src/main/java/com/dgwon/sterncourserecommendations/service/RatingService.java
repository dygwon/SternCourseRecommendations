package com.dgwon.sterncourserecommendations.service;

import com.dgwon.sterncourserecommendations.domain.Course;
import com.dgwon.sterncourserecommendations.domain.Rating;
import com.dgwon.sterncourserecommendations.repo.CourseRepository;
import com.dgwon.sterncourserecommendations.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private CourseRepository courseRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public RatingService(CourseRepository courseRepository, RatingRepository ratingRepository) {
        this.courseRepository = courseRepository;
        this.ratingRepository = ratingRepository;
    }

    // Create
    public Rating createRating(String courseCode, int rating, boolean recommended, String comment) {
        Course course = this.courseRepository.findById(courseCode).orElseThrow(() ->
                new RuntimeException("Course does not exist: " + courseCode));

        return this.ratingRepository.save(new Rating(course, rating, recommended, comment));
    }

    // Read
    public Iterable<Rating> lookup() {
        return this.ratingRepository.findAll();
    }

    // Read by courseCode
//    public Iterable<Rating> lookupByCourseCode(String courseCode) {
//        return this.ratingRepository.findAllByCourse(courseCode);
//    }

    // Update

    // Delete

}
