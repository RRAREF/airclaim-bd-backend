package com.airclaimbd.airclaimbackend.dto;

public class AdminLoginResponse {

    private boolean success;
    private String username;
    private String message;

    public AdminLoginResponse() {
    }

    public AdminLoginResponse(boolean success, String username, String message) {
        this.success = success;
        this.username = username;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}