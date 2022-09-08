package com.dgwon.sterncourserecommendations;

import com.dgwon.sterncourserecommendations.domain.Department;
import com.dgwon.sterncourserecommendations.service.CourseService;
import com.dgwon.sterncourserecommendations.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SternCourseRecommendationsApplication implements CommandLineRunner {
    @Autowired
    private CourseService courseService;

    @Autowired
    private RatingService ratingService;

    public static void main(String[] args) {
        SpringApplication.run(SternCourseRecommendationsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createCourses();
        createRatings();
    }

    private void createCourses() {
        this.courseService.createCourse("TECH-GB2131", "High Tech Entrepreneurship", Department.TECHNOLOGY);
        this.courseService.createCourse("TECH-GB2135", "Programming in Python", Department.TECHNOLOGY);
        this.courseService.createCourse("TECH-GB2318", "Digital Strategy", Department.TECHNOLOGY);
        this.courseService.createCourse("OPMG-GB2308", "Retail Operations & Supply Chain Management", Department.OPERATIONS_MANAGEMENT);
        this.courseService.createCourse("OPMG-GB2313", "Operations in Entertainment: Las Vegas", Department.OPERATIONS_MANAGEMENT);
        this.courseService.createCourse("OPMG-GB2316", "Operations Consulting: An Experiential Approach", Department.OPERATIONS_MANAGEMENT);
    }

    private void createRatings() {
        this.ratingService.createRating("TECH-GB2131", 5, true, "Good overview of the topic");
        this.ratingService.createRating("OPMG-GB2308", 2, false, "Basically the same as operations management");
    }
}
