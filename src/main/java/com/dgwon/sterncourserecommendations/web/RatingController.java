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
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/courses/{courseId}/ratings")
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
    public RatingDto createRating(@PathVariable(value = "courseId") String courseId,
                             @RequestBody @Validated RatingDto ratingDto) {
        Course course = verifyCourse(courseId);
        Rating newRating = ratingRepository.save(new Rating(new RatingPk(course, ratingDto.getUserId()),
                ratingDto.getRating(), ratingDto.getComment(), ratingDto.isRecommended()));
        return new RatingDto(newRating);
    }

    @GetMapping
    public Page<RatingDto> getAllRatingsForCourse(@PathVariable(value = "courseId") String courseId,
                                                  Pageable pageable) {
        verifyCourse(courseId);
        Page<Rating> ratings = ratingRepository.findByPkCourseId(courseId, pageable);
        return new PageImpl<RatingDto>(
                ratings.get().map(RatingDto::new).collect(Collectors.toList()),
                pageable,
                ratings.getTotalElements()
        );
    }

    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(@PathVariable(value = "courseId") String courseId) {
        verifyCourse(courseId);
        return Map.of("average", ratingRepository.findByPkCourseId(courseId).stream()
                .mapToInt(Rating::getRating).average()
                .orElseThrow(() -> new NoSuchElementException("Course has no ratings")));
    }

    @PutMapping
    public RatingDto updateWithPut(@PathVariable(value = "courseId") String courseId,
                                   @RequestBody @Validated RatingDto ratingDto) {
        Rating rating = verifyRating(courseId, ratingDto.getUserId());
        rating.setRating(ratingDto.getRating());
        rating.setComment(ratingDto.getComment());
        rating.setRecommended(ratingDto.isRecommended());
        return new RatingDto(ratingRepository.save(rating));
    }

    @PatchMapping
    public RatingDto updateWithPatch(@PathVariable(value = "courseId") String courseId,
                                     @RequestBody @Validated RatingDto ratingDto) {
        Rating rating = verifyRating(courseId, ratingDto.getUserId());
        if (ratingDto.getRating() != null)
            rating.setRating(ratingDto.getRating());
        if (ratingDto.getComment() != null)
            rating.setComment(ratingDto.getComment());
        if (ratingDto.isRecommended() != null)
            rating.setRecommended(ratingDto.isRecommended());
        return new RatingDto(ratingRepository.save(rating));
    }

    @DeleteMapping(path = "/{userId}")
    public void delete(@PathVariable(value = "courseId") String courseId,
                       @PathVariable(value = "userId") String userId) {
        Rating rating = verifyRating(courseId, userId);
        ratingRepository.delete(rating);
    }

    /* -----------------------------------------HELPERS----------------------------------------------------*/
    private Course verifyCourse(String courseId) throws NoSuchElementException {
        return courseRepository.findById(courseId).orElseThrow(() ->
                new NoSuchElementException("Course does not exist " + courseId));
    }

    private Rating verifyRating(String courseId, String userId) throws NoSuchElementException {
        return ratingRepository.findByPkCourseIdAndPkUserId(courseId, userId).orElseThrow(() ->
                new NoSuchElementException("(" + courseId + ", " + userId + ") is not a valid Course-Rating pair"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException e) {
        return e.getMessage();
    }
}
