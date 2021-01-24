package com.stuart.studynoteapi.models.dto.responses;

public class ServerResponse {

    private String message;
    private Boolean successful;

    public ServerResponse(String message, Boolean successful) {
        this.message = message;
        this.successful = successful;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }
}

