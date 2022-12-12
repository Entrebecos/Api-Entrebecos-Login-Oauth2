package com.example.demo.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

// Validar email digitado pelo usuário
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        TODO: Regex to validate email
        return true;
    }
}
