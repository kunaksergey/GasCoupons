package ua.station.model.service;

import ua.station.model.entity.User;
import ua.station.model.entity.UserDto;
import ua.station.model.exception.EmailExistsException;

import java.util.List;
import java.util.Optional;

/**
 * Created by sa on 06.11.17.
 */
public interface UserService {

    User findByEmail(String emai);

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    Iterable<User> findAll();

    User findById(int id);

    void save(User user);
}
