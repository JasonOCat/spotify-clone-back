package com.jason.spotifycloneback.dto;

import lombok.Builder;

@Builder
public record ReadUserDTO(
        String firstName,
        String lastName,
        String email,
        String imageUrl
) {
}
