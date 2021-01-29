package com.example.handmadeBackend.controller;

import com.example.handmadeBackend.dto.UserAuthenticationDto;
import com.example.handmadeBackend.security.config.SecurityConfig;
import com.example.handmadeBackend.security.exception.AuthenticationRequestException;
import com.example.handmadeBackend.security.jwt.JwtResponse;
import com.example.handmadeBackend.security.jwt.JwtTokenUtil;
import com.example.handmadeBackend.security.jwt.JwtUserDetailsService;
import com.example.handmadeBackend.security.service.AuthenticationRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Log4j2
@SecurityConfig
@RestController
@CrossOrigin
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationRequestService authenticationRequestService;

    @GetMapping("/login")
    public ResponseEntity<?> authentication(@Valid @RequestBody UserAuthenticationDto authenticationRequest, HttpServletRequest httpServletRequest) throws Exception {

        if(!authenticationRequestService.checkAuthPossibility(httpServletRequest.getRemoteAddr()))
            throw new AuthenticationRequestException("Too many login attempts");

        UserDetails userDetails = null;
        try{
            authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
            userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        }
        catch (Exception e){
            authenticationRequestService.createFailedRequest(authenticationRequest.getUsername(), httpServletRequest.getRemoteAddr());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        authenticationRequestService.cleanHistory(httpServletRequest.getRemoteAddr());

        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
