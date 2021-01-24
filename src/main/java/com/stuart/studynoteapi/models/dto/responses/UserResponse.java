package com.stuart.studynoteapi.models.dto.responses;

import com.stuart.studynoteapi.models.Folder;
import com.stuart.studynoteapi.models.UserSet;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Set;

public class UserResponse implements Serializable {

    public UserResponse(Set<Folder> folders, Set<UserSet> userSets){
        this.folders = folders;
        this.userSets = userSets;
    }

    @Nullable
    public Set<UserSet> userSets;

    @Nullable
    public Set<UserSet> getUserSets() {
        return userSets;
    }

    @Nullable
    Set<Folder> folders;

    public void setUserSets(@Nullable Set<UserSet> userSets) {
        this.userSets = userSets;
    }

    @Nullable
    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(@Nullable Set<Folder> folders) {
        this.folders = folders;
    }



}
