package com.jason.spotifycloneback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SongDTO(
        @NotBlank String title,
        @NotBlank String artist,
        @NotNull byte[] cover,
        @NotBlank String coverContentType,
        @NotNull UUID publicId
) {
}
