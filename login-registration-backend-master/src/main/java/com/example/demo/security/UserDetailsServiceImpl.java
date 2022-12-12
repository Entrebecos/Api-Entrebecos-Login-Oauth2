package com.example.demo.security;

import com.example.demo.model.AppUser;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// Classe que será responsável por ter as ações do usuário
// como cadastrar, ativar conta e verificar se a conta é válida
// ao realizar login

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    private final static String USER_NOT_FOUND_MSG =
            "Usuário com email %s não encontrado.";

//    @Override
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//        AppUser appUser = appUserRepository.getAppUserByEmail(email);
//
//        if (appUser == null) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//
//        return new MyUserDetails(appUser);
//    }

    // Validação do usuário quando realizar Login
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.getAppUserByEmail(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return new MyUserDetails(appUser);
    }

    // Cadastro do usuário e envio do Token por email
    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO checar se os atributos são os mesmos e
            // TODO se o email não estiver confirmado, enviar email
            // TODO confirmação

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token;
    }

    // Ativar o usuário
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

}
