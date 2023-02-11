package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

public class ReviewDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Preecha o campo requerido.")
    private String text;

    private Long movieId;

    private UserDTO user;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String text, MovieDTO movieId, UserDTO user) {
        this.id = id;
        this.text = text;
        this.movieId = movieId.getId();
        this.user = user;
    }

    public ReviewDTO(Review entity) {
        id = entity.getId();
        text = entity.getText();
        movieId = entity.getMovie().getId();
        user = new UserDTO(entity.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public UserDTO getuser() {
        return user;
    }

    public void setuser(UserDTO user) {
        this.user = user;
    }
}
