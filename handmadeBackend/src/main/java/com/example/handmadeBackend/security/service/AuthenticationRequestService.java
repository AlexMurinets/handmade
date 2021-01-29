package com.example.handmadeBackend.security.service;


import com.example.handmadeBackend.security.enums.AuthenticationRequestStatus;
import com.example.handmadeBackend.security.exception.AuthenticationRequestException;
import com.example.handmadeBackend.security.model.AuthenticationRequest;
import com.example.handmadeBackend.security.repository.AuthenticationRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AuthenticationRequestService {
    private final AuthenticationRequestRepository authenticationRequestRepository;

    public void createFailedRequest(String username, String ip) throws AuthenticationRequestException {
        AuthenticationRequest authenticationRequest;
        if(authenticationRequestRepository.existsByIp(ip)){
            authenticationRequest = authenticationRequestRepository.findByIp(ip);

            if(authenticationRequest.getAuthenticationRequestStatus().equals(AuthenticationRequestStatus.DEFAULT))
                authenticationRequest.setAuthenticationRequestStatus(AuthenticationRequestStatus.FAILED);

            else if(authenticationRequest.getAuthenticationRequestStatus().equals(AuthenticationRequestStatus.FAILED))
                authenticationRequest.setAuthenticationRequestStatus(AuthenticationRequestStatus.DANGEROUS);

            else if (authenticationRequest.getAuthenticationRequestStatus().equals(AuthenticationRequestStatus.DANGEROUS)){
                sendVerificationMail();
                throw new AuthenticationRequestException("Too many login attempts");
            }
        }
        else {
            authenticationRequest = new AuthenticationRequest();
            authenticationRequest.setIp(ip);
            authenticationRequest.setUsername(username);
            authenticationRequest.setData(new Date());
        }
        authenticationRequestRepository.save(authenticationRequest);
    }

    public boolean checkAuthPossibility(String ip){
        if(authenticationRequestRepository.existsByIp(ip))
            return !authenticationRequestRepository.findByIp(ip).getAuthenticationRequestStatus().equals(AuthenticationRequestStatus.DANGEROUS);
        else
            return true;
    }

    public void cleanHistory(String ip){
        if(authenticationRequestRepository.existsByIp(ip))
            authenticationRequestRepository.delete(authenticationRequestRepository.findByIp(ip));
    }
    public void sendVerificationMail(){

    }
}
