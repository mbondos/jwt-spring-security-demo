package com.invoiceproject.api.controllers;


import com.invoiceproject.api.model.ApplicationUser;
import com.invoiceproject.api.services.ApplicationUserService;
import com.invoiceproject.api.services.UserAuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/public/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PublicApplicationUserController {
    @NonNull
    UserAuthenticationService authenticationService;
    @NonNull
    ApplicationUserService applicationUserService;

    @PostMapping("/register")
    String register(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        //TODO refactor to DTO
        applicationUserService.save(ApplicationUser.builder()
                .username(username)
                .password(password)
                .build());
        return login(username, password);
    }

    @PostMapping("/login")
    String login(String username, String password) {
        return authenticationService
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid login and/or password"));
    }
}
