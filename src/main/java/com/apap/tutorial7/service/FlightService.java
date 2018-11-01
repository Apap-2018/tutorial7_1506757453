package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;

/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    
    void deleteByFlightNumber(String flightNumber);
    
    FlightModel updateFlight(FlightModel flight);
    
    void deleteFlight(FlightModel flight);
    
    List<FlightModel> findAllFlights();
    
    Optional<FlightModel> getFlightById(long id);

    Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);
}