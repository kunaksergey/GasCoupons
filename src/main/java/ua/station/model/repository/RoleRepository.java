package ua.station.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.station.model.entity.Role;

/**
 * Created by sa on 06.11.17.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
