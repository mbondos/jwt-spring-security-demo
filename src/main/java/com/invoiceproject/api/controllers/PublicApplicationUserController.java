package com.invoiceproject.api.controllers;


import com.invoiceproject.api.dtos.UsernameAndPasswordDto;
import com.invoiceproject.api.model.ApplicationUser;
import com.invoiceproject.api.services.ApplicationUserService;
import com.invoiceproject.api.services.UserAuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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
    String register(@RequestBody UsernameAndPasswordDto usernameAndPasswordDto) {
        applicationUserService.save(ApplicationUser.builder()
                .username(usernameAndPasswordDto.getUsername())
                .password(usernameAndPasswordDto.getPassword())
                .build());
        return login(usernameAndPasswordDto);
    }

    @PostMapping("/login")
    String login(@RequestBody UsernameAndPasswordDto usernameAndPasswordDto) {
        return authenticationService
                .login(usernameAndPasswordDto.getUsername(), usernameAndPasswordDto.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid login and/or password"));
    }
}
