package com.stuart.studynoteapi.models.dto.requests;

import com.stuart.studynoteapi.models.Folder;

import java.util.Set;

public class FolderRequest {

    private String email;
    private Set<Folder> folders;

    public FolderRequest(String email, Set<Folder> folders) {
        this.email = email;
        this.folders = folders;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;
    }
}