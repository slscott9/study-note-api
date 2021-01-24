package com.stuart.studynoteapi.models.dto.requests;

import com.stuart.studynoteapi.models.UserSet;

import java.util.Set;

public class SetRequest {


    private Set<UserSet> userSets;
    private String userEmail;

    public SetRequest(Set<UserSet> userSets, String userEmail) {
        this.userSets = userSets;
        this.userEmail = userEmail;
    }


    public Set<UserSet> getUserSets() {
        return userSets;
    }

    public void setUserSets(Set<UserSet> userSets) {
        this.userSets = userSets;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}