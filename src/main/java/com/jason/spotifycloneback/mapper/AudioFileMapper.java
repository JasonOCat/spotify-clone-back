package com.jason.spotifycloneback.mapper;

import com.jason.spotifycloneback.dto.CreateSongDTO;
import com.jason.spotifycloneback.entity.AudioFile;
import org.springframework.stereotype.Service;

@Service
public class AudioFileMapper {

    public AudioFile createSongDTOToAudioFile(CreateSongDTO createSongDTO) {
        return AudioFile.builder()
                .file(createSongDTO.audioFile())
                .fileContentType(createSongDTO.audioFileContentType())
                .build();
    }
}
