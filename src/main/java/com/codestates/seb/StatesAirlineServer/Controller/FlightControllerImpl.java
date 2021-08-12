package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import com.codestates.seb.StatesAirlineServer.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightControllerImpl implements FlightController{

    private final FlightService flightService;

    @Autowired
    public FlightControllerImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    @GetMapping(value = "/flight")
    public List<FlightDTO.Info> FilterFlightList(@RequestParam(required = false) String departure_times,
                                                 @RequestParam(required = false) String arrival_times,
                                                 @RequestParam(required = false) String departure,
                                                 @RequestParam(required = false) String destination) {

        if(departure_times != null && arrival_times != null){
            return flightService.SreachByTime(departure_times,arrival_times);
        }
        else if(departure != null && destination != null){
            return flightService.SreachByRoute(departure,destination);
        }
        else{
            return flightService.SreachAll();
        }
    }

    @Override
    @GetMapping(value = "flight/{id}")
    public FlightDTO.Info FindById(@PathVariable String id) {

        return flightService.SreachById(id);
    }

    @Override
    @PutMapping(value = "flight/{id}")
    public FlightDTO.Info UpdateFlightData(@PathVariable String id,
                                           @RequestBody(required = false) FlightDTO.Request data) {

        return flightService.UpdateFlight(id,data);
    }
}
