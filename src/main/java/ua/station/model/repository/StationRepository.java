package ua.station.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.station.model.entity.Station;

@Repository
public interface StationRepository extends CrudRepository<Station,Integer>{
}
