package edu.de.uni.passau.webeng.students.application.service;

import edu.de.uni.passau.webeng.students.application.Roles;
import edu.de.uni.passau.webeng.students.persistence.entities.User;
import edu.de.uni.passau.webeng.students.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getOne(username);

        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            // This stream looks complicated but just converts the role list to a string array. You can also do that without a stream.
            builder.roles(user.getRoles().stream().map(Roles::toString).toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }


}
