package com.application.demo.Book_my_show.controller;

import com.application.demo.Book_my_show.entity.TicketEntity;
import com.application.demo.Book_my_show.requestdtos.TicketRequestDto;
import com.application.demo.Book_my_show.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController
{
    @Autowired
    TicketService ticketService;


    @PostMapping("/add")
    public ResponseEntity<?> bookTickets(@RequestBody TicketRequestDto ticketRequestDto) {
        try {
            String response = ticketService.addTicket(ticketRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String msg = "movie could not be added" + e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getticketlessthan500price")
    public ResponseEntity<?> getticketlessthan500price() {
        try {
            List<TicketEntity> response = ticketService.getTicketsByPriceLessThan500();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String msg = "ticket could not be found" + e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
