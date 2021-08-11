package com.codestates.seb.StatesAirlineServer.Controller;

import com.codestates.seb.StatesAirlineServer.Data.FlightData;
import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FlightControllerImpl implements FlightController{

    private List<FlightDTO.Info> flightList = FlightData.getInstacne().getFlightList();
    @Override
    public List<FlightDTO.Info> FilterFlightList(@RequestParam(required = false) String departure_times,
                                                 @RequestParam(required = false) String arrival_times,
                                                 @RequestParam(required = false) String departure,
                                                 @RequestParam(required = false) String destination) {

        // 출발 시간과 도착 시간이 null이 아닌 경우
        if(departure_times != null && arrival_times != null){
            return flightList
                    .stream()
                    .filter(item -> item.getDeparture_times().equals(departure_times))
                    .filter(item -> item.getArrival_times().equals(arrival_times))
                    .collect(Collectors.toList());

        }
        // 출발 장소와 도착 장소가 null이 아닌 경우
        else if(departure != null && destination != null){
            return flightList
                    .stream()
                    .filter(item -> item.getDeparture().equals(departure))
                    .filter(item -> item.getDestination().equals(destination))
                    .collect(Collectors.toList());
        }
        // 그 밖에
        else{
            return flightList;
        }
    }

    @Override
    @GetMapping(value = "/flight/{id}")
    public FlightDTO.Info FindById(@PathVariable(value = "uuid") String id) {
        return flightList
                .stream()
                .filter(item -> item.getUuid().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public FlightDTO.Info UpdateFlightData(String id, FlightDTO.Request data) {
        return null;
    }
}
