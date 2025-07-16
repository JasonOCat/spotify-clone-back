package com.jason.spotifycloneback.mapper;

import com.jason.spotifycloneback.dto.CreateSongDTO;
import com.jason.spotifycloneback.dto.SongDTO;
import com.jason.spotifycloneback.entity.Song;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SongMapper {

    public SongDTO songToSongDTO(Song song) {
        return SongDTO.builder()
                .title( song.getTitle())
                .artist(song.getArtist())
                .cover(song.getCover())
                .coverContentType(song.getCoverContentType())
                .publicId(song.getPublicId())
                .build();
    }

    public Song createSongDTOToSong(CreateSongDTO createSongDTO) {
        return Song.builder()
                .publicId(UUID.randomUUID())
                .title(createSongDTO.title())
                .artist(createSongDTO.artist())
                .cover(createSongDTO.cover())
                .coverContentType(createSongDTO.coverContentType())
                .build();
    }
}
