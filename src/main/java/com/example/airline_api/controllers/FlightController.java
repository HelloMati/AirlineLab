package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlightById(@PathVariable Long id){
        Flight foundFlight = flightService.findFlight(id);
        return new ResponseEntity(foundFlight, HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.addNewFlight(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

// Book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable Long id, @RequestBody String passengerName) {
        Flight updatedFlight = flightService.addPassengerToFlight(id, passengerName);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancelFlight(@PathVariable Long id) {
        flightService.cancelFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}