package com.jason.spotifycloneback.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FavoriteSongDTO(
        @NotNull boolean isFavorite,
        @NotNull UUID publicId
) {

}
