package com.example.handmadeBackend.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}
