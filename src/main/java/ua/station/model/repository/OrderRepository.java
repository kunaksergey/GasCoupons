package ua.station.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.station.model.entity.Order;

/**
 * Created by sa on 07.11.17.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order,Integer>{
}
