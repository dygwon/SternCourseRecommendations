package com.dgwon.sterncourserecommendations.repo;

import com.dgwon.sterncourserecommendations.domain.Rating;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;


public interface RatingRepository extends PagingAndSortingRepository<Rating, Integer> {
    @Override
    @RestResource(exported = false)
    <S extends Rating> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Rating> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Rating entity);

    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Rating> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
