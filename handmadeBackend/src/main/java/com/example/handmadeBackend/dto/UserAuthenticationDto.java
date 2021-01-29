package com.example.handmadeBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationDto implements Serializable {
    private String username;
    private String password;
}
