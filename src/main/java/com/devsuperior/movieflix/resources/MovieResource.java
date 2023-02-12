package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movieDTO = service.findById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MovieMinDTO>> findByGenreWithMovieSortedAlphabetically(
            @RequestParam(name = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieMinDTO> pageOfMovies = service.findByGenreWithMovieSortedAlphabetically(genreId, pageable);
        return ResponseEntity.ok().body(pageOfMovies);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewByMovieId(@PathVariable Long id) {
        List<ReviewDTO> reviews = service.findReviewByMovieId(id);
        return ResponseEntity.ok().body(reviews);
    }
}
