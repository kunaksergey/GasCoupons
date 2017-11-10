package ua.station.model.service;

import ua.station.model.entity.Station;

/**
 * Created by sa on 07.11.17.
 */
public interface StationService {
    Iterable<Station> findAll();
    Station findOne(int id);
    void save(Station station);
}
