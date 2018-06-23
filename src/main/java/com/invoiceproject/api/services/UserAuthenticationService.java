package com.invoiceproject.api.services;

import com.invoiceproject.api.model.ApplicationUser;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<String> login(String username, String password);
    Optional<ApplicationUser> findByToken(String token);
    void logout(ApplicationUser user);
}
