package ua.station.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.station.model.entity.Role;
import ua.station.model.entity.User;
import ua.station.model.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sa on 06.11.17.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        Set<GrantedAuthority> setGrantedAuthority = new HashSet();
        setGrantedAuthority.addAll(getSetGrantedAuthority(user.getRoles()));
        if (!user.getStatus() && !user.getRoles().contains(new Role("ROLE_ADMIN"))) {
            throw new UsernameNotFoundException("User is not enable");
        }
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        setGrantedAuthority);
        return userDetails;
    }


    private Set<GrantedAuthority> getSetGrantedAuthority(Set<Role> setRole) {
        Set<GrantedAuthority> grantedAuthoritiesSet = new HashSet<>();
        setRole.forEach(r -> {
            grantedAuthoritiesSet.add(new SimpleGrantedAuthority(r.getRole())
            );
        });
        return grantedAuthoritiesSet;
    }
}
