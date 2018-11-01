package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

/**
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;
    
    @Override
    public FlightModel addFlight(FlightModel flight) {
        return flightDb.save(flight);
    }

    @Override
    public void deleteByFlightNumber(String flightNumber) {
        flightDb.deleteByFlightNumber(flightNumber);
    }

    @Override
    public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }

	@Override
	public Optional<FlightModel> getFlightById(long id) {
		// TODO Auto-generated method stub
		return flightDb.findById(id);
	}
	
	@Override
	public FlightModel updateFlight(FlightModel flight) {
		FlightModel flightToUpdate = flightDb.getOne(flight.getId());
		flightToUpdate.setDestination(flight.getDestination());
		flightToUpdate.setFlightNumber(flight.getFlightNumber());
		flightToUpdate.setOrigin(flight.getOrigin());
		flightToUpdate.setPilot(flight.getPilot());
		flightToUpdate.setTime(flight.getTime());
		
//        flightDb.save(flightToUpdate);
        
        return flightToUpdate;
	}

	@Override
	public List<FlightModel> findAllFlights() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.delete(flight);
	}
}