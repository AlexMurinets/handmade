package com.example.handmadeBackend.security.model;

import com.example.handmadeBackend.security.enums.AuthenticationRequestStatus;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class AuthenticationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    private String ip;

    @DateTimeFormat
    private Date data;

    @Enumerated(EnumType.STRING)
    AuthenticationRequestStatus authenticationRequestStatus = AuthenticationRequestStatus.DEFAULT;
}
