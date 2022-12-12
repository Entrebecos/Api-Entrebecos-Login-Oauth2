package com.example.demo.service;

import com.example.demo.model.AuthenticationType;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.model.AppUser;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;


    public void updateAuthenticationType(String username, String oauth2ClientName) {
        AuthenticationType authType = AuthenticationType.valueOf(oauth2ClientName.toUpperCase());
        appUserRepository.updateAuthenticationType(username, authType);
        System.out.println("Updated user's authentication type to " + authType);
    }

    @Transactional
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }


}
