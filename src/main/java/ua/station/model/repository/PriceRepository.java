package ua.station.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.station.model.entity.Price;

/**
 * Created by sa on 05.11.17.
 */
@Repository
public interface PriceRepository extends JpaRepository<Price,Integer> {

}
