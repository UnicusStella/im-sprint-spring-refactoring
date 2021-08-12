package com.codestates.seb.StatesAirlineServer.Service;

import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import com.codestates.seb.StatesAirlineServer.Repository.FlightRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepositoryImpl flightRepositoryImpl;

    @Autowired
    public FlightServiceImpl(FlightRepositoryImpl flightRepositoryImpl) {
        this.flightRepositoryImpl = flightRepositoryImpl;
    }

    @Override
    public List<FlightDTO.Info> SreachAll() {

        return flightRepositoryImpl.FindAll();
    }

    @Override
    public List<FlightDTO.Info> SreachByTime(String departure_times, String arrival_times) {

        return flightRepositoryImpl.FindByTime(departure_times, arrival_times);
    }

    @Override
    public List<FlightDTO.Info> SreachByRoute(String departure, String destination) {

        return flightRepositoryImpl.FindByRoute(departure, destination);

    }

    @Override
    public FlightDTO.Info SreachById(String id) {

        return flightRepositoryImpl.FindById(id).get();

    }

    @Override
    public FlightDTO.Info UpdateFlight(String id, FlightDTO.Request data) {

        return flightRepositoryImpl.Update(id, data);

    }
    
    // 메인 브런치 에서는 따로 작업 합니다.~ 테스트
}
