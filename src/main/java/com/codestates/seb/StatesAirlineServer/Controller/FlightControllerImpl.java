package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import com.codestates.seb.StatesAirlineServer.Service.FlightService;
import com.codestates.seb.StatesAirlineServer.Service.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightControllerImpl implements FlightController{

    private final FlightServiceImpl flightServiceImpl;

    @Autowired
    public FlightControllerImpl(FlightServiceImpl flightServiceImpl) {
        this.flightServiceImpl = flightServiceImpl;
    }

    @Override
    @GetMapping(value = "/flight")
    public List<FlightDTO.Info> FilterFlightList(@RequestParam(required = false) String departure_times,
                                                 @RequestParam(required = false) String arrival_times,
                                                 @RequestParam(required = false) String departure,
                                                 @RequestParam(required = false) String destination) {

        if(departure_times != null && arrival_times != null){
            return flightServiceImpl.SreachByTime(departure_times,arrival_times);
        }
        else if(departure != null && destination != null){
            return flightServiceImpl.SreachByRoute(departure,destination);
        }
        else{
            return flightServiceImpl.SreachAll();
        }
    }

    @Override
    @GetMapping(value = "flight/{id}")
    public FlightDTO.Info FindById(@PathVariable String id) {

        return flightServiceImpl.SreachById(id);
    }

    @Override
    @PutMapping(value = "flight/{id}")
    public FlightDTO.Info UpdateFlightData(@PathVariable String id,
                                           @RequestBody(required = false) FlightDTO.Request data) {

        return flightServiceImpl.UpdateFlight(id,data);
    }
}
