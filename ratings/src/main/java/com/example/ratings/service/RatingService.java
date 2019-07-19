package com.example.ratings.service;

import com.example.ratings.domain.Rating;
import com.example.ratings.exception.RatingNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {
    /**
     * Hardcoded values for example purpose
     */
    public static List<Rating> fakeDatabase = new ArrayList<>();

    public List<Rating> findAllRatings() {
        return fakeDatabase;
    }

    public Rating findRatingById(Long id) {
        return fakeDatabase.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElseThrow(RatingNotFoundException::new);
    }

    public Rating createRating(Rating rating) {
        fakeDatabase.add(rating);
        return rating;
    }

    public void deleteRating(Long id) {
        fakeDatabase.remove(
                this.findRatingById(id)
        );
    }

    public Rating updateRating(Rating updatedRanking, Long idToUpdate) {
        Rating oldRating = this.findRatingById(idToUpdate);
        updatedRanking.setId( oldRating.getId() );
        this.deleteRating(oldRating.getId());
        fakeDatabase.add(updatedRanking);
        return updatedRanking;
    }

    public List<Rating> findRatingsByMovieId(Long movieId) {
        return fakeDatabase.stream()
                .filter(e -> e.getMovieId().equals(movieId))
                .collect(Collectors.toList());
    }
}
