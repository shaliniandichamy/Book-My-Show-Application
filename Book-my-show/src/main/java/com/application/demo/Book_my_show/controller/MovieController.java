package com.application.demo.Book_my_show.controller;

import com.application.demo.Book_my_show.entity.MovieEntity;
import com.application.demo.Book_my_show.enums.Language;
import com.application.demo.Book_my_show.requestdtos.MovieRequestDto;
import com.application.demo.Book_my_show.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController
{

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        try {
            String response = movieService.addMovie(movieRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String msg = "movie could not be added" + e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMovieByMovieName")
    public ResponseEntity<?> getMovieByMovieName(@RequestParam String movieName) {
        try
        {
             MovieEntity movieEntity= movieService.getMovieByMovieName(movieName);
            return new ResponseEntity<>(movieEntity, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            String msg = "movie could not be found" + e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMovieByLanguage")
    public ResponseEntity<?> getMovieByLanguage(@RequestParam Language language) {
        try
        {
            List<MovieEntity> movieEntityList= movieService.getMovieByLanguage(language);
            return new ResponseEntity<>(movieEntityList, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            String msg = "movie could not be found" + e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
