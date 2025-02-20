package com.application.demo.Book_my_show.controller;

import com.application.demo.Book_my_show.entity.ShowEntity;
import com.application.demo.Book_my_show.enums.ShowType;
import com.application.demo.Book_my_show.requestdtos.ShowRequestDto;
import com.application.demo.Book_my_show.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController
{
    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<?> addShow(@RequestBody ShowRequestDto showRequestDto)
    {
        try {
            String response = showService.addShow(showRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String msg = "show could not be added" + e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getshowbyshowtype")
    public ResponseEntity<?> getShowByShowType(@RequestParam("showtype")ShowType showType)
    {
        try {
            List<ShowEntity> response = showService.getShowByShowType(showType);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String msg = "show could not be added" + e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
