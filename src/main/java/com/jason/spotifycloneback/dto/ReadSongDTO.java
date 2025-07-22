package com.jason.spotifycloneback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ReadSongDTO(
        @NotBlank String title,
        @NotBlank String artist,
        @NotNull byte[] cover,
        @NotBlank String coverContentType,
        boolean isFavorite,
        @NotNull UUID publicId
) {

    public ReadSongDTO withFavorite(boolean favorite) {
        return ReadSongDTO.builder()
                .title(title)
                .artist(artist)
                .cover(cover)
                .coverContentType(coverContentType)
                .isFavorite(favorite)
                .publicId(publicId)
                .build();
    }
}
