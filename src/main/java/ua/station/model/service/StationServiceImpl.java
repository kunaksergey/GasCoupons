package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.station.model.entity.Station;
import ua.station.model.repository.StationRepository;

import java.util.Optional;


/**
 * Created by sa on 07.11.17.
 */
@Service("stationService")
public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;

    @Override
    public Iterable<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Station findOne(int id) {
        Optional<Station> byId = stationRepository.findById(id);
        return (byId.isPresent()) ? byId.get() : null;
    }

    @Override
    public void save(Station station) {
        stationRepository.save(station);
    }
}
