package com.dgwon.sterncourserecommendations.repo;


import com.dgwon.sterncourserecommendations.domain.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course, String> {
    List<Course> findByTitle(@Param("title") String title);
    List<Course> findByDepartment(@Param("department") String department);

    @Override
    @RestResource(exported = false)
    <S extends Course> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Course> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(Course entity);

    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Course> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
