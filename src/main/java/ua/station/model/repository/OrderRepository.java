package ua.station.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.station.model.entity.Order;

/**
 * Created by sa on 07.11.17.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order,Integer>{
    Iterable<Order> findAllByStatus(int status);

    @Modifying
    @Query("update Order o set o.status = ?2 where o.id = ?1")
    int changeStatus(int id, int status);
}
