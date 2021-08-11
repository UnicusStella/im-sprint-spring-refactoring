package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;

import java.util.List;

public class FlightControllerImpl implements FlightController{
    @Override
    public List<FlightDTO.Info> FilterFlightList(String departure_times, String arrival_times, String departure, String destination) {
        return null;
    }

    @Override
    public FlightDTO.Info FindById(String id) {
        return null;
    }

    @Override
    public FlightDTO.Info UpdateFlightData(String id, FlightDTO.Request data) {
        return null;
    }
}
