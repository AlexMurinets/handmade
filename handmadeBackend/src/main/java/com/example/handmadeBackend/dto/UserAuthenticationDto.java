package com.example.handmadeBackend.dto;

import com.sun.istack.NotNull;
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

    @NotNull
    private String username;
    @NotNull
    private String password;
}
