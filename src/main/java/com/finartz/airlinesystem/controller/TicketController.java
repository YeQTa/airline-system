package com.finartz.airlinesystem.controller;

import com.finartz.airlinesystem.dto.ticket.TicketApiInput;
import com.finartz.airlinesystem.dto.ticket.TicketApiOutput;
import com.finartz.airlinesystem.dto.ticket.TicketServiceInput;
import com.finartz.airlinesystem.dto.ticket.TicketServiceOutput;
import com.finartz.airlinesystem.service.ticket.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Yekta Anil AKSOY
 * @since : 3.02.2021
 **/

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/buy")
    public ResponseEntity<TicketApiOutput> buyTicket(@RequestBody TicketApiInput ticketApiInput) {
        final TicketServiceOutput ticketServiceOutput = ticketService
                .buyTicket(modelMapper.map(ticketApiInput,
                        TicketServiceInput.class));
        return new ResponseEntity<>(modelMapper.map(ticketServiceOutput, TicketApiOutput.class),
                HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<TicketApiOutput> searchTicket(
            @RequestParam("ticketCode") String ticketCode) {
        return new ResponseEntity<>(
                modelMapper.map(ticketService.searchTicket(ticketCode), TicketApiOutput.class),
                HttpStatus.OK);
    }

    @GetMapping(value = "/return")
    public ResponseEntity<TicketApiOutput> returnTicket(
            @RequestParam("ticketCode") String ticketCode) {
        return new ResponseEntity<>(
                modelMapper.map(ticketService.returnTicket(ticketCode), TicketApiOutput.class),
                HttpStatus.OK);
    }
}
