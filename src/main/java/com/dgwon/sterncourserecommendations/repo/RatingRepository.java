package com.dgwon.sterncourserecommendations.repo;

import com.dgwon.sterncourserecommendations.domain.Rating;
import com.dgwon.sterncourserecommendations.domain.RatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface RatingRepository extends CrudRepository<Rating, RatingPk> {
    List<Rating> findByPkCourseId(String id);
    Optional<Rating> findByPkCourseIdAndPkUserId(String id, String userId);
    Page<Rating> findByPkCourseId(String courseId, Pageable pageable);
}
