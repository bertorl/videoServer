package com.aromero.videoserver.services.film.model;

import com.aromero.videoserver.services.actor.model.Actor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Film")
public class Film {

    @Id
    private Long id;
    private String title;
    private List<Actor> actors;
    private Date addedDate;
    private String posterID;
    private String cloudFilmURL;

    public Film() {
        super();
    }

    public Film(Long id, String title, List<Actor> actors, Date addedDate, String cloudFilmURL) {
        this.id = id;
        this.title = title;
        this.actors = actors;
        this.addedDate = addedDate;
        this.cloudFilmURL = cloudFilmURL;
    }

    public static Film buildFilm (FilmDao filmDao) {
        return new Film(filmDao.getId(), filmDao.getTitle(),
                filmDao.getActors(), filmDao.getAddedDate(), filmDao.getFilmExternalURL());
    }
}
