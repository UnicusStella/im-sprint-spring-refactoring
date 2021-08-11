package com.codestates.seb.StatesAirlineServer.Repository;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;

import java.util.List;
import java.util.Optional;

public class FlightRepositoryImpl implements FlightRepository{
    @Override
    public List<FlightDTO.Info> FindAll() {
        return null;
    }

    @Override
    public List<FlightDTO.Info> FindByTime(String departure_times, String arrival_times) {
        return null;
    }

    @Override
    public List<FlightDTO.Info> FindByRoute(String departure, String destination) {
        return null;
    }

    @Override
    public Optional<FlightDTO.Info> FindById(String id) {
        return Optional.empty();
    }

    @Override
    public FlightDTO.Info Update(String id, FlightDTO.Request data) {
        return null;
    }
}
