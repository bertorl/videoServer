package com.aromero.videoserver.services.film.service;

import com.aromero.videoserver.services.film.model.Film;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FilmService {
    public List<Film> getAllFilms();
    public Mono<Resource> getPosterFromFilm(Film film);
    public Film addFilm(Film film);
    public void addPoster(Resource resource, Film film);
    public Mono<Resource> getPosterFromPosterID(String posterID);
}

