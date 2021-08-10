package com.codestates.seb.StatesAirlineServer.Repository;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {
    public List<FlightDTO.Info> FindAll();
    public List<FlightDTO.Info> FindByTime(String departure_times, String arrival_times);
    public List<FlightDTO.Info> FindByRoute(String departure, String destination);
    public Optional<FlightDTO.Info> FindById(String id);
    public FlightDTO.Info Update(String id, FlightDTO.Request data);
}
