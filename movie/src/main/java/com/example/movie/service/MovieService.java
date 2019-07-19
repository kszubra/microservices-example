package com.example.movie.service;

import com.example.movie.domain.Movie;
import com.example.movie.exception.MovieNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    /**
     * Hardcoded values for example purpose
     */
    public static List<Movie> fakeDatabase = new ArrayList<>();

    public List<Movie> findAllMovies() {
        return fakeDatabase;
    }

    public Movie findMovieById(Long id) {
        return fakeDatabase.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElseThrow(MovieNotFoundException::new);
    }

    public Movie createMovie(Movie movie) {
        fakeDatabase.add(movie);
        return movie;
    }

    public void deleteMovie(Long id) {
        fakeDatabase.remove(
                this.findMovieById(id)
        );
    }

    public Movie updateMovie(Movie oldMovie, Movie updatedMovie) {
        updatedMovie.setId( oldMovie.getId() );
        this.deleteMovie(oldMovie.getId());
        fakeDatabase.add(updatedMovie);
        return updatedMovie;
    }
}
