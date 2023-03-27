package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    HashMap<String,Movie> MovieDB;
    HashMap<String,Director> DirectorDB;
    HashMap<Director, List<Movie>> DirectorList;

    public MovieRepository() {
        MovieDB = new HashMap<>();
        DirectorDB = new HashMap<>();
        DirectorList = new HashMap<>();
    }

    public void addMovie(Movie movie){
        String movieName = movie.getName();
        MovieDB.put(movieName,movie);
    }

    public void addDirector(Director director){
        String directorName = director.getName();
        DirectorDB.put(directorName,director);
    }

    public void addPair(Director director,Movie movie){
        List<Movie> dList;
        if(DirectorList.containsKey(director)){
           dList  = DirectorList.get(director);
        }
        else {
            dList = new ArrayList<>();
        }
        dList.add(movie);
        DirectorList.put(director,dList);
    }

    public Director getDirector(String name) {
        return DirectorDB.get(name);
    }

    public Movie getMovie(String name){
        return MovieDB.get(name);
    }

    public List<Movie> getMovieList(Director director){
        return DirectorList.get(director);
    }

    public List<String> getAllMovies(){
        List<String> allMovies = MovieDB.keySet().stream().collect(Collectors.toList());
        return allMovies;
    }

    public void deleteDirector(Director director){
        String name = director.getName();
        DirectorDB.remove(name);

        List<Movie> Dlist = getMovieList(director);

        for(Movie mv : Dlist){
            deleteMovie(mv);
        }

        DirectorList.remove(director);
    }

    public void deleteAllDirectors(){
        for(Director director : DirectorList.keySet()){
            deleteDirector(director);
        }
    }


    public void deleteMovie(Movie movie){
        String name = movie.getName();
        MovieDB.remove(name);
    }

}
