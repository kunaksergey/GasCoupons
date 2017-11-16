package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.station.model.entity.Basket;
import ua.station.model.entity.Role;
import ua.station.model.entity.User;
import ua.station.model.entity.UserDto;
import ua.station.model.exception.EmailExistsException;
import ua.station.model.repository.BasketRepository;
import ua.station.model.repository.UserRepository;

import java.util.Optional;

/**
 * Created by sa on 04.11.17.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BasketRepository basketRepository;
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            +  accountDto.getEmail());
        }
        User user=new User(accountDto.getEmail(), accountDto.getPassword());
        user.getRoles().add(new Role("ROLE_USER"));
        user=userRepository.save(user);
        basketRepository.save(new Basket(user));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.isPresent()?byId.get():null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
