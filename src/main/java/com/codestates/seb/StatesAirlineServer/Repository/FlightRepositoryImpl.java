package com.codestates.seb.StatesAirlineServer.Repository;

import com.codestates.seb.StatesAirlineServer.Data.FlightData;
import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

    private final List<FlightDTO.Info> flightList = FlightData.getInstacne().getFlightList();

    @Override
    public List<FlightDTO.Info> FindAll() {

        return flightList;
    }

    @Override
    public List<FlightDTO.Info> FindByTime(String departure_times, String arrival_times) {
        return flightList
                .stream()
                .filter(item -> item.getDeparture_times().equals(departure_times))
                .filter(item -> item.getArrival_times().equals(arrival_times))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDTO.Info> FindByRoute(String departure, String destination) {

        return flightList
                .stream()
                .filter(item -> item.getDeparture().equals(departure))
                .filter(item -> item.getDestination().equals(destination))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightDTO.Info> FindById(String id) {

        return flightList
                .stream()
                .filter(item -> item.getUuid().equals(id))
                .findAny();
    }

    @Override
    public FlightDTO.Info Update(String id, FlightDTO.Request data) {

        FlightDTO.Info filterData = flightList
                .stream()
                .filter(item -> item.getUuid().equals(id))
                .findAny()
                .get();

        if (data.getDeparture() != null) filterData.setDeparture(data.getDeparture());
        if (data.getDestination() != null) filterData.setDestination(data.getDestination());
        if (data.getDeparture_times() != null) filterData.setDeparture_times(data.getDeparture_times());
        if (data.getArrival_times() != null) filterData.setArrival_times(data.getArrival_times());

        return filterData;
    }
}
