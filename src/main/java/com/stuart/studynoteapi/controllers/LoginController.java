package com.stuart.studynoteapi.controllers;

import com.stuart.studynoteapi.models.dto.UserDto;
import com.stuart.studynoteapi.models.dto.responses.ServerResponse;
import com.stuart.studynoteapi.security.MyUserDetailsService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
    ResponseEntity is the whole HTTP response including status code, headers, body
 */

@RestController
public class LoginController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @PostMapping("/login")

    public ResponseEntity<ServerResponse> login(@RequestBody UserDto userToCheck) {


        try{
            UserDetails user = myUserDetailsService.loadUserByUsername(userToCheck.getUserName());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = user.getPassword();

            if(passwordEncoder.matches(userToCheck.getPassword(),encodedPassword )){
                return new ResponseEntity<ServerResponse>(new ServerResponse("Success", true), HttpStatus.OK);
            }else {
                System.out.print("username is " + user.getUsername() + "password is " + user.getPassword());
                return new ResponseEntity<ServerResponse>( new ServerResponse("Failed", false), HttpStatus.UNAUTHORIZED);
            }

        }catch (UsernameNotFoundException usernameNotFoundException) {
            System.out.print("username not found exception");
            return new ResponseEntity<ServerResponse>( new ServerResponse("Failed", false), HttpStatus.UNAUTHORIZED);
        }
//        System.out.println(userToCheck.getUserName());
//        System.out.println(userToCheck.getPassword());
//
//
//        UserDetails user = myUserDetailsService.loadUserByUsername(userToCheck.getUserName());
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = user.getPassword();
//
//        if (passwordEncoder.matches(userToCheck.getPassword(), encodedPassword)) {
//            return new ResponseEntity(new ServerResponse("Success", false), HttpStatus.OK);
//        } else {
//            return new ResponseEntity(new ServerResponse("Username or password is incorrect", false), HttpStatus.UNAUTHORIZED);
//        }

    }
}

