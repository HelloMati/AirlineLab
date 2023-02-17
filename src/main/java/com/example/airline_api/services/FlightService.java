package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight findFlight(Long id) {
        return flightRepository.findById(id).get();
    }
    public Flight addNewFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight addPassengerToFlight(Long id, String passengerName) {
        Optional<Flight> optionalFlight = getFlightById(id);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            Passenger passenger = new Passenger(passengerName);
            passengerRepository.save(passenger);
            List<Passenger> passengers = flight.getPassengers();
            passengers.add(passenger);
            flight.setPassengers(passengers);
            return flightRepository.save(flight);
        }
        return null;
    }

    public void cancelFlight(Long id) {
        flightRepository.deleteById(id);
    }

}