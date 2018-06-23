package com.invoiceproject.api.services;

import com.google.common.collect.ImmutableMap;
import com.invoiceproject.api.model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TokenAuthenticationService implements UserAuthenticationService {
    @NonNull
    TokenService tokenService;
    @NonNull
    ApplicationUserService userService;


    @Override
    public Optional<String> login(String username, String password) {
        return userService
                .findByUsername(username)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> tokenService.expiring(ImmutableMap.of("username", username)));
    }

    @Override
    public Optional<ApplicationUser> findByToken(String token) {
        return Optional
                .of(tokenService.verify(token))
                .map(map -> map.get("username"))
                .flatMap(userService::findByUsername);
    }

    @Override
    public void logout(ApplicationUser user) {

    }
}
