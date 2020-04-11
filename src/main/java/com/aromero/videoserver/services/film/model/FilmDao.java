package com.aromero.videoserver.services.film.model;

import com.aromero.videoserver.services.actor.model.Actor;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Data
public class FilmDao {

    private Long id;
    private String title;
    private List<Actor> actors;
    private Date addedDate;
    private String posterID;
    private String filmExternalURL;

    public FilmDao() {
        super();
    }

    public FilmDao(Long id, String title, List<Actor> actors, Date addedDate, String posterID, String filmExternalURL) {
        super();
        this.id = id;
        this.title = title;
        this.actors = actors;
        this.addedDate = addedDate;
        this.posterID = posterID;
        this.filmExternalURL = filmExternalURL;
    }

    public FilmDao(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.actors = film.getActors();
        this.addedDate = film.getAddedDate();
        this.posterID = film.getPosterID();
        this.filmExternalURL = film.getCloudFilmURL();
    }
}
