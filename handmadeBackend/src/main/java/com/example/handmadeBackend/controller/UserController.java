package com.example.handmadeBackend.controller;

import com.example.handmadeBackend.dto.UserDto;
import com.example.handmadeBackend.request.UserRequest;
import com.example.handmadeBackend.response.UserResponse;
import com.example.handmadeBackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        UserResponse userResponse = modelMapper.map(createdUser, UserResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
