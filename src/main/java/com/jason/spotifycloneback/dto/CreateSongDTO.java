package com.jason.spotifycloneback.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateSongDTO(
        @NotBlank String title,
        @NotBlank String artist,
        @NotNull byte[] cover,
        @NotNull String coverContentType,
        @NotNull byte[] audioFile,
        @NotNull String audioFileContentType
) {
}
