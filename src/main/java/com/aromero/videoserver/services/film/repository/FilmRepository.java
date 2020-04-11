package com.aromero.videoserver.services.film.repository;

import com.aromero.videoserver.services.film.model.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends MongoRepository<Film, Long> {
}
