package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('MEMBER')")
    @Transactional
    public ReviewDTO insertReview(ReviewDTO reviewDTO) {
        Review entityReview = new Review();
        copyDtoToEntity(entityReview, reviewDTO);
        entityReview = repository.save(entityReview);
        return new ReviewDTO(entityReview);
    }

    private void copyDtoToEntity(Review entityReview, ReviewDTO reviewDTO) {
        entityReview.setId(reviewDTO.getId());
        entityReview.setText(reviewDTO.getText());
        entityReview.setUser(authService.authenticated());
        Movie movie = movieRepository.getOne(reviewDTO.getMovieId());
        entityReview.setMovie(movie);
    }

}
