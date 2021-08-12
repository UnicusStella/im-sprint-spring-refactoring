package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import com.codestates.seb.StatesAirlineServer.Repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<FlightDTO.Info> SreachAll() {

        return flightRepository.FindAll();
    }

    @Override
    public List<FlightDTO.Info> SreachByTime(String departure_times, String arrival_times) {

        return flightRepository.FindByTime(departure_times, arrival_times);
    }

    @Override
    public List<FlightDTO.Info> SreachByRoute(String departure, String destination) {

        return flightRepository.FindByRoute(departure, destination);

    }

    @Override
    public FlightDTO.Info SreachById(String id) {

        return flightRepository.FindById(id).get();

    }

    @Override
    public FlightDTO.Info UpdateFlight(String id, FlightDTO.Request data) {

        return flightRepository.Update(id, data);

    }
    
    // 메인 브런치 에서는 따로 작업 합니다.~ 테스트
}
