package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Optional<Movie> optionalMovie = repository.findById(id);
        Movie obj = optionalMovie.orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return new MovieDTO(obj, obj.getGenre());
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @Transactional(readOnly = true)
    public Page<MovieMinDTO> findByGenreWithMovieSortedAlphabetically(Long genreId, Pageable pageable) {
        Page<MovieMinDTO> pageOfMovies = repository.findByGenreWithMovieSortedAlphabetically(genreId, pageable);
        return pageOfMovies;
    }
}
