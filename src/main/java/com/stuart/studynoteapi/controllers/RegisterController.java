package com.stuart.studynoteapi.controllers;

import com.stuart.studynoteapi.models.User;
import com.stuart.studynoteapi.models.dto.UserDto;
import com.stuart.studynoteapi.models.dto.responses.ServerResponse;
import com.stuart.studynoteapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public  RegisterController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ServerResponse register(@RequestBody UserDto userDto){
        User user = new User(
                userDto.getEmail(),
                userDto.getUserName(),
                passwordEncoder.encode(userDto.getPassword()),
                true,
                "USER",
                userDto.getFolders(),
                userDto.getSets()
        );

        userRepository.save(user);

        return new ServerResponse("Successfully registered", true);
    }
}
