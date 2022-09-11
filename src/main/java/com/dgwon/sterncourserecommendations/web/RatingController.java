package com.dgwon.sterncourserecommendations.web;

import com.dgwon.sterncourserecommendations.domain.Course;
import com.dgwon.sterncourserecommendations.domain.Rating;
import com.dgwon.sterncourserecommendations.domain.RatingPk;
import com.dgwon.sterncourserecommendations.repo.CourseRepository;
import com.dgwon.sterncourserecommendations.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/courses/{courseCode}/ratings")
public class RatingController {
    RatingRepository ratingRepository;
    CourseRepository courseRepository;

    @Autowired
    public RatingController(RatingRepository ratingRepository, CourseRepository courseRepository) {
        this.ratingRepository = ratingRepository;
        this.courseRepository = courseRepository;
    }

    protected RatingController() {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RatingDto createRating(@PathVariable(value = "courseCode") String courseCode,
                             @RequestBody @Validated RatingDto ratingDto) {
        Course course = verifyCourse(courseCode);
        Rating newRating = ratingRepository.save(new Rating(new RatingPk(course, ratingDto.getStudentId()),
                ratingDto.getRating(), ratingDto.getComment(), ratingDto.isRecommended()));
        return new RatingDto(newRating);
    }

    @GetMapping
    public Page<RatingDto> getAllRatingsForCourse(@PathVariable(value = "courseCode") String courseCode,
                                                  Pageable pageable) {
        verifyCourse(courseCode);
        Page<Rating> ratings = ratingRepository.findByPkCourseCode(courseCode, pageable);
        return new PageImpl<RatingDto>(
                ratings.get().map(RatingDto::new).collect(Collectors.toList()),
                pageable,
                ratings.getTotalElements()
        );
    }

    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(@PathVariable(value = "courseCode") String courseCode) {
        verifyCourse(courseCode);
        return Map.of("average", ratingRepository.findByPkCourseCode(courseCode).stream()
                .mapToInt(Rating::getRating).average()
                .orElseThrow(() -> new NoSuchElementException("Course has no ratings")));
    }

    @PutMapping
    public RatingDto updateWithPut(@PathVariable(value = "courseCode") String courseCode,
                                   @RequestBody @Validated RatingDto ratingDto) {
        Rating rating = verifyRating(courseCode, ratingDto.getStudentId());
        rating.setRating(ratingDto.getRating());
        rating.setComment(ratingDto.getComment());
        rating.setRecommended(ratingDto.isRecommended());
        return new RatingDto(ratingRepository.save(rating));
    }

    @PatchMapping
    public RatingDto updateWithPatch(@PathVariable(value = "courseCode") String courseCode,
                                     @RequestBody @Validated RatingDto ratingDto) {
        Rating rating = verifyRating(courseCode, ratingDto.getStudentId());
        if (ratingDto.getRating() != null)
            rating.setRating(ratingDto.getRating());
        if (ratingDto.getComment() != null)
            rating.setComment(ratingDto.getComment());
        if (ratingDto.isRecommended() != null)
            rating.setRecommended(ratingDto.isRecommended());
        return new RatingDto(ratingRepository.save(rating));
    }

    @DeleteMapping(path = "/{userId}")
    public void delete(@PathVariable(value = "courseCode") String courseCode,
                       @PathVariable(value = "userId") Integer studentId) {
        Rating rating = verifyRating(courseCode, studentId);
        ratingRepository.delete(rating);
    }

    /* -----------------------------------------HELPERS----------------------------------------------------*/
    private Course verifyCourse(String courseCode) throws NoSuchElementException {
        return courseRepository.findById(courseCode).orElseThrow(() ->
                new NoSuchElementException("Course does not exist " + courseCode));
    }

    private Rating verifyRating(String courseCode, Integer studentId) throws NoSuchElementException {
        return ratingRepository.findByPkCourseCodeAndPkStudentId(courseCode, studentId).orElseThrow(() ->
                new NoSuchElementException("(" + courseCode + ", " + studentId + ") is not a valid Course-Rating pair"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException e) {
        return e.getMessage();
    }
}
