package com.example.demo.registration;

import com.example.demo.model.AppUser;
import com.example.demo.registration.RegistrationRequest;
import com.example.demo.registration.RegistrationService;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.demo.model.AuthenticationType.USER;

@RestController
@AllArgsConstructor
@RequestMapping(path = "entrebecos")
public class RegistrationController {

    private final RegistrationService registrationService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserService appUserService;

    private final AppUserRepository appUserRepository;

    // Cadastrar usuário
    @PostMapping("/cadastro")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    // Confirmar token
    @GetMapping(path = "cadastro/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    // Alterar usuário
    @PreAuthorize("hasAnyRole('ADMIN','USER','FACEBOOK', 'GOOGLE')")
    @PutMapping("/perfil/{email}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "email") String email,
                                             @RequestBody AppUser appUser) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
        if (!appUserOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUser.setEmail(appUserOptional.get().getEmail());
        appUser.setAuthType(USER);

        return ResponseEntity.status(HttpStatus.OK).body(appUserService.save(appUser));
    }

}


