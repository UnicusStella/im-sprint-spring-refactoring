package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Data.BookData;
import com.codestates.seb.StatesAirlineServer.Data.FlightData;
import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FlightServiceImpl implements FlightService{

    private List<FlightDTO.Info> flightList = FlightData.getInstacne().getFlightList();


    @Override
    @GetMapping(value = "/flight")
    public List<FlightDTO.Info> SreachAll() {

    return flightList;
    }

    @Override
    @GetMapping(value = "/flight")
    public List<FlightDTO.Info> SreachByTime(@RequestBody(required = false) String departure_times,
                                             @RequestBody(required = false) String arrival_times) {

        if (departure_times != null && arrival_times != null){
            return flightList
                    .stream()
                    .filter(item -> item.getDeparture_times().equals(departure_times))
                    .filter(item -> item.getArrival_times().equals(arrival_times))
                    .collect(Collectors.toList());

        }
        return flightList;
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
