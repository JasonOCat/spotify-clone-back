package com.jason.spotifycloneback.dto;

import jakarta.persistence.Lob;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AudioFileDTO(
        UUID publicId,
        @Lob byte[] file,
        String fileContentType
) {
}
