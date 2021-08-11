package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;


import java.util.List;

public interface FlightService {
    public List<FlightDTO.Info> SreachAll();
    public List<FlightDTO.Info> SreachByTime(String departure_times, String arrival_times);
    public List<FlightDTO.Info> SreachByRoute(String departure, String destination);
    public FlightDTO.Info SreachById(String id);
    public FlightDTO.Info UpdateFlight(String id, FlightDTO.Request data);


}
