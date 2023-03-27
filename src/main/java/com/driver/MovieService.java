package com.driver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    MovieRepository movieRepository = new MovieRepository();

    public String addMovie(Movie movie) {
        movieRepository.addMovie(movie);
        return "Movie added successfully";
    }

    public void addDirector(Director director){
        movieRepository.addDirector(director);
    }

    public void addPair(String directorName, String movieName){
        Director director = movieRepository.getDirector(directorName);
        Movie movie = movieRepository.getMovie(movieName);

        movieRepository.addPair(director,movie);
    }
    public Movie getMovie(String name){
        return movieRepository.getMovie(name);
    }

    public Director getDirector(String name){
        return movieRepository.getDirector(name);
    }
    public List<String> getMovieList(String name){
        Director director = movieRepository.getDirector(name);

        List<Movie> MovieList = movieRepository.getMovieList(director);

        List<String> list = new ArrayList<>();

        for(Movie movie : MovieList){
            list.add(movie.getName());
        }
        return list;
    }

    public List<String> getAllMovies(){
        return movieRepository.getAllMovies();
    }

    public void deleteDirector(String directorName){
        Director director = movieRepository.getDirector(directorName);
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectors();
    }


}
