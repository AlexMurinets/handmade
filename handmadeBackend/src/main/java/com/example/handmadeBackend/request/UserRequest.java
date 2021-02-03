package com.example.handmadeBackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "Username is required.")
    private String username;
    @NotNull(message = "Firstname is required.")
    private String firstName;
    @NotNull(message = "Lastname is required.")
    private String lastName;
    private String description;
    @NotNull(message = "Password is required.")
    @Size(min = 8, max = 12)
    private String password;
}
