package com.driver;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;


@RestController
@RequestMapping ("/movies")
public class MovieController {

    MovieService movieService = new MovieService();
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie (@RequestBody Movie movie){
        String returnMessage =  movieService.addMovie(movie);

        return new ResponseEntity<String>(returnMessage, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);

        return new ResponseEntity<>("Director added successfully",HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addPair(@RequestParam String directorName, String movieName){
        movieService.addPair(directorName,movieName);

        return new ResponseEntity<>("Movie-Director pair created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
            Movie movie = movieService.getMovie(name);

            return new ResponseEntity<>(movie,HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director = movieService.getDirector(name);

        return  new ResponseEntity<>(director,HttpStatus.CREATED);
    }

    @GetMapping("get-movies-by-director-name/{director}")
    public  ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> MovieList = movieService.getMovieList(director);

        return new ResponseEntity<>(MovieList,HttpStatus.CREATED);
    }

    @GetMapping("get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> allMovies = movieService.getAllMovies();

        return new ResponseEntity<>(allMovies,HttpStatus.CREATED);
    }

    @DeleteMapping("delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
        movieService.deleteDirector(directorName);

        return new ResponseEntity<>("Director and movies deleted successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();

        return new ResponseEntity<>("All directors deleted successfully",HttpStatus.CREATED);
    }


}
