package com.invoiceproject.api.services;

import com.invoiceproject.api.model.ApplicationUser;
import com.invoiceproject.api.repositories.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public Long save(ApplicationUser applicationUser) {
        applicationUserRepository.save(applicationUser);
        return applicationUser.getApplicationUserId();
    }

    public Optional<ApplicationUser> findByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }
}
