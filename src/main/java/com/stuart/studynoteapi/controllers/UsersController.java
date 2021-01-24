package com.stuart.studynoteapi.controllers;

import com.stuart.studynoteapi.models.Folder;
import com.stuart.studynoteapi.models.Term;
import com.stuart.studynoteapi.models.User;
import com.stuart.studynoteapi.models.UserSet;
import com.stuart.studynoteapi.models.dto.requests.SetRequest;
import com.stuart.studynoteapi.models.dto.responses.ServerResponse;
import com.stuart.studynoteapi.models.dto.responses.UserResponse;
import com.stuart.studynoteapi.repository.FolderRepository;
import com.stuart.studynoteapi.repository.SetRepository;
import com.stuart.studynoteapi.repository.TermRepository;
import com.stuart.studynoteapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TermRepository termRepository;
    @Autowired
    SetRepository setRepository;
    @Autowired
    FolderRepository folderRepository;

    //new routes

    @PostMapping("/add/folder/{userEmail}")
    public ResponseEntity<Folder> addFolder(@RequestBody Folder folder, @PathVariable String userEmail){
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);

        System.out.println(optionalUser.get().getEmail().toString());

        if(!optionalUser.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        folder.setUser(optionalUser.get());

        Folder savedFolder = folderRepository.save(folder);

        return new ResponseEntity<Folder>( savedFolder, HttpStatus.OK);
    }

    @PostMapping("/add/set/{userEmail}")
    public ResponseEntity<UserSet> addSet(@RequestBody  UserSet userSet, @PathVariable String userEmail){
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);

        if(!optionalUser.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        userSet.setUser(optionalUser.get());
        userSet.setIsSynced(true);

        for(Term term : userSet.getTerms()){
            term.setUser(optionalUser.get());
            term.setUserSet(userSet);
            term.setSynced(true);
        }

        UserSet savedSet = setRepository.save(userSet);


        return new ResponseEntity<>(savedSet, HttpStatus.OK);


    }


    @PostMapping("/add/set/folder/{folderId}")
    public ResponseEntity<ServerResponse> addSetsToFolder(@RequestBody List<Long> setIds, @PathVariable Long folderId){
        Optional<Folder> optionalFolder = folderRepository.findById(folderId);

        if(!optionalFolder.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        for(Long id : setIds){
            Optional<UserSet> setToAdd = setRepository.findById(id);
            if(setToAdd.isPresent()){
                optionalFolder.get().setUserSets(setToAdd.get());
                setToAdd.get().setFolderId(optionalFolder.get().getId());

            }

        }
        optionalFolder.get().setSetCount((String.valueOf(setIds.size())));
        folderRepository.save(optionalFolder.get());
        return new ResponseEntity<>(new ServerResponse("Successfully added sets to folder", true), HttpStatus.OK);

    }


    @PostMapping("/delete/folder/{folderId}")
    public ResponseEntity<ServerResponse> deleteFolder(@PathVariable Long folderId){
        folderRepository.deleteById(folderId);



        return new ResponseEntity<ServerResponse>(new ServerResponse("Successfully deleted folder", true), HttpStatus.OK);
    }






}



