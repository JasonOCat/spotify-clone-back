package com.jason.spotifycloneback.mapper;


import com.jason.spotifycloneback.dto.ReadUserDTO;
import com.jason.spotifycloneback.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public ReadUserDTO readUserDTOToUser(User user) {
        return ReadUserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .build();
    }
}
