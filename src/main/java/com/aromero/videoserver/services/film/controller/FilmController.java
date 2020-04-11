package com.aromero.videoserver.services.film.controller;

import com.aromero.videoserver.core.FileService;
import com.aromero.videoserver.services.film.model.Film;
import com.aromero.videoserver.services.film.model.FilmDao;
import com.aromero.videoserver.services.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/films")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD})
public class FilmController {

    private final FilmService filmService;
    private final FileService fileService;

    @Autowired
    public FilmController(FilmService filmService, FileService fileService) {
        this.filmService = filmService;
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public Flux<FilmDao> getAllFilms() {
        List<Film> films = this.filmService.getAllFilms();
        return Flux.fromIterable(films.stream().map(
                film -> new FilmDao(film))
                .collect(Collectors.toList()));
    }

    @GetMapping("/poster/{id}")
    public Mono<Resource> getPoster(@PathVariable("id") String posterID) {
        return this.filmService.getPosterFromPosterID(posterID);
    }

    @PostMapping(value = "/poster")
    public Mono<ResponseEntity<FilmDao>> postPoster( @RequestPart("file") FilePart filePart) {

        System.out.println(filePart.filename());
      //  serverRequest.body(BodyExtractors.toMultipartData()).flatMap(
     //           parts -> {
      //              Map<String, Part> map = parts.toSingleValueMap();
       //             FilePart filePart = (FilePart) map.get("file");
      //              return Mono.just(new ResponseEntity<>(new FilmDao(), HttpStatus.CREATED));
      //          }
      //  );
      //  FilePart partFile;
       // Flux<Object> returnType = parts.flatMap(part -> {
      //     partFile = (FilePart) part;
            String a = "a";
       //     return null;
        FilmDao test = new FilmDao();
        test.setPosterID("aaaaaaaaaaaaa");
       // });
        return Mono.just(new ResponseEntity<>(test, HttpStatus.CREATED));
    }

    @GetMapping("/sorted")
    public Flux<FilmDao> getFilmsSortedByTimeAdded(@RequestParam("direction") String direction) {


        return null;
    }

    @PostMapping("/one")
    public Mono<ResponseEntity<FilmDao>> addNewFilm(@RequestBody FilmDao film) {
        Film savedFilm = this.filmService.addFilm(Film.buildFilm(film));
        return Mono.just(new ResponseEntity<>(new FilmDao(savedFilm), HttpStatus.CREATED));
    }
}
