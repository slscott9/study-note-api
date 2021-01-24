package com.stuart.studynoteapi.security;

import com.stuart.studynoteapi.models.MyUserDetails;
import com.stuart.studynoteapi.models.User;
import com.stuart.studynoteapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
    JPA spring security does not support auth out of the box

    So we must provide a user service that can provide authentication with user details(password and username)

    spring security will then use these user details to perform authentication, checking if the user details

    this class gives it match
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }
}
