package com.ub.service;

public interface SecurityService {
    String findLoggedInUsername();

    void login(String username, String password);
    
}