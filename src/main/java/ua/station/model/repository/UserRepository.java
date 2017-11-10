package ua.station.model.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.station.model.entity.User;

/**
 * Created by sa on 05.11.17.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Integer>
{
    User findByEmail(String email);

}
