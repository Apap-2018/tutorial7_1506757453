package com.apap.tutorial7.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.service.FlightService;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @PostMapping(value = "/add")
    public FlightModel addFlight (@RequestBody FlightModel flight) {
    	return flightService.addFlight(flight);
    }
    
    @PutMapping(value = "/update/{flightID}")
    public String updateFlight (@PathVariable("flightID") long flightID,
    		@RequestParam(value="destination") Optional<String> destination,
    		@RequestParam(value="origin") Optional<String> origin,
    		@RequestParam(value="time") @DateTimeFormat(pattern="yyyy-MM-dd") Optional<Date> time) {
    	FlightModel flight = flightService.getFlightById(flightID).get();
    	if(flight.equals(null)) {
    		return "Couldn't find the flight";
    	}
    	
    	if(destination.isPresent()) {
    		flight.setDestination(destination.get());
    	}
    	if(origin.isPresent()) {
    		flight.setOrigin(origin.get());
    	}
    	if(time.isPresent()) {
    		flight.setTime(time.get());
    	}
    	
    	flightService.updateFlight(flight);
    	return "flight update success";
    }
    
    @GetMapping(value = "/view/{flightNumber}")
    public FlightModel viewFlight(@PathVariable("flightNumber") String flightNumber) {
    	FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber).get();
    	return flight;
    }
    
    @GetMapping(value = "/all")
    public List<FlightModel> viewAllFlight() {
    	List<FlightModel> allFlights = flightService.findAllFlights();
    	return allFlights;
    }
    
    @DeleteMapping(value = "/{flightID}")
    public String deleteFlight(@PathVariable("flightID") long flightID) {
    	FlightModel flight = flightService.getFlightById(flightID).get();
    	flightService.deleteFlight(flight);
    	return "flight has been deleted";
    }
    

    
}