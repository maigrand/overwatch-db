package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.payload.AuthenticationCredentials;
import com.maigrand.overwatchdb.payload.AuthenticationTokenDetails;
import com.maigrand.overwatchdb.repository.UserRepository;
import com.maigrand.overwatchdb.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    public UserService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public AuthenticationTokenDetails authenticate(
            @Valid AuthenticationCredentials credentials) {
        Authentication authenticationData = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(),
                credentials.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationData);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication, credentials.isRememberMe());
        return new AuthenticationTokenDetails(token);
    }
}
