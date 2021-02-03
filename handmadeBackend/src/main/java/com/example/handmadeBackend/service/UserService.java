package com.example.handmadeBackend.service;

import com.example.handmadeBackend.dto.ProductDto;
import com.example.handmadeBackend.dto.UserDto;
import com.example.handmadeBackend.model.Product;
import com.example.handmadeBackend.model.User;
import com.example.handmadeBackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto createUser(UserDto userDto){
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        UserDto returnUser = modelMapper.map(user, UserDto.class);
        return returnUser;
    }
}
