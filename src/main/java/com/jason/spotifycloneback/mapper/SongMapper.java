package com.jason.spotifycloneback.mapper;

import com.jason.spotifycloneback.dto.CreateSongDTO;
import com.jason.spotifycloneback.dto.ReadSongDTO;
import com.jason.spotifycloneback.entity.Song;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SongMapper {

    public ReadSongDTO songToSongDTO(Song song) {
        return ReadSongDTO.builder()
                .title(song.getTitle())
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

    public ReadSongDTO songToReadSongDTO(Song song) {
        return ReadSongDTO.builder()
                .title(song.getTitle())
                .artist(song.getArtist())
                .cover(song.getCover())
                .coverContentType(song.getCoverContentType())
                .publicId(song.getPublicId())
                .build();
    }
}
