package com.invoiceproject.api.controllers;

import com.invoiceproject.api.model.ApplicationUser;
import com.invoiceproject.api.services.UserAuthenticationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class SecuredApplicationUserController {
    @NonNull
    UserAuthenticationService authenticationService;

    @GetMapping("/current")
    ApplicationUser getCurrent(@AuthenticationPrincipal final ApplicationUser applicationUser) {
        return applicationUser;
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final ApplicationUser applicationUser) {
        authenticationService.logout(applicationUser);
        return true;
    }
}
