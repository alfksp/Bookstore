//package com.application.bookstore.service;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//    private CustomUserDetailsService customUserDetailsService;
//    private AuthenticationManager authenticationManager;
//    public AuthenticationService(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager) {
//        this.customUserDetailsService = customUserDetailsService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    public boolean authenticate(String username, String password) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            return true;
//        }catch (AuthenticationException e) {
//            return false;
//        }
//    }
//}
