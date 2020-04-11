package com.aromero.videoserver.services.film.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.aromero.videoserver.config.FileSystemConfiguration;
import com.aromero.videoserver.core.FileService;
import com.aromero.videoserver.services.film.model.Film;
import com.aromero.videoserver.services.film.repository.FilmRepository;
import com.aromero.videoserver.utils.RandomGeneratorUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Service(FilmServiceImpl.BEAN_NAME)
public class FilmServiceImpl implements FilmService {

    public static final String BEAN_NAME = "FilmService";

    private final FilmRepository filmRepository;
    private final FileService fileService;
    private final String FILM_PATH;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, FileService fileService,
                           FileSystemConfiguration fileSystemConfiguration) {
        this.filmRepository = filmRepository;
        this.fileService = fileService;
        this.FILM_PATH = fileSystemConfiguration.getPath() + "/films";
    }

    @Override
    public List<Film> getAllFilms() {
        return this.filmRepository.findAll();
    }

    @Override
    public Mono<Resource> getPosterFromFilm(Film film) {
        String uri = this.FILM_PATH + "/" + film.getId() + "/" + film.getPosterID();
        return this.fileService.getFileAsyncFromFileSystem(uri);
    }

    @Override
    public Film addFilm(Film film) {
        film.setId(RandomGeneratorUUID.generateRandomID());
        film.setAddedDate(new Date());
        film.setPosterID(film.getId() + ".jpg");
        return this.filmRepository.save(film);

    }

    @Override
    public void addPoster(Resource resource, Film film) {
        Film res = this.filmRepository.findById(film.getId()).get();
        if(res != null) {
            // this.fileService
        }
    }

    @Override
    public Mono<Resource> getPosterFromPosterID(String posterID) {
        StringTokenizer tokens = new StringTokenizer(posterID, ".");
        String uri = this.FILM_PATH + "/" + tokens.nextToken() + "/" + posterID;
        return this.fileService.getFileAsyncFromFileSystem(uri);
    }


}
