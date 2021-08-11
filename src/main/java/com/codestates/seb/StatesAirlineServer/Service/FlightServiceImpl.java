package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;

import java.util.List;

public class FlightServiceImpl implements FlightService{
    @Override
    public List<FlightDTO.Info> SreachAll() {
        return null;
    }

    @Override
    public List<FlightDTO.Info> SreachByTime(String departure_times, String arrival_times) {
        return null;
    }

    @Override
    public List<FlightDTO.Info> SreachByRoute(String departure, String destination) {
        return null;
    }

    @Override
    public FlightDTO.Info SreachById(String id) {
        return null;
    }

    @Override
    public FlightDTO.Info UpdateFlight(String id, FlightDTO.Request data) {
        return null;
    }
}
