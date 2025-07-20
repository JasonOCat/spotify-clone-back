package com.jason.spotifycloneback.service;

import com.jason.spotifycloneback.dto.AudioFileDTO;
import com.jason.spotifycloneback.dto.CreateSongDTO;
import com.jason.spotifycloneback.dto.SongDTO;
import com.jason.spotifycloneback.entity.AudioFile;
import com.jason.spotifycloneback.entity.Song;
import com.jason.spotifycloneback.mapper.AudioFileMapper;
import com.jason.spotifycloneback.mapper.SongMapper;
import com.jason.spotifycloneback.repository.AudioFileRepository;
import com.jason.spotifycloneback.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SongService {

    private final SongRepository songRepository;

    private final AudioFileRepository audioFileRepository;

    private final SongMapper songMapper;

    private final AudioFileMapper audioFileMapper;

    @Transactional(readOnly = true)
    public List<SongDTO> getAll() {
        return songRepository.findAll()
                .stream()
                .map(songMapper::songToSongDTO)
                .toList();
    }

    public SongDTO create(CreateSongDTO createSongDTO) {
        // Create song
        Song song = songMapper.createSongDTOToSong(createSongDTO);
        Song songSaved = songRepository.save(song);

        // Create the audio file
        AudioFile audioFile = audioFileMapper.createSongDTOToAudioFile(createSongDTO);

        // Link the song and the AudioFile
        audioFile.setSong(songSaved);
        audioFileRepository.save(audioFile);

        return songMapper.songToSongDTO(songSaved);
    }

    public Optional<AudioFileDTO> getOneByPublicId(UUID publicId) {
        Optional<AudioFile> audioFile = audioFileRepository.findOneBySongPublicId(publicId);
        return audioFile.map(audioFileMapper::audioFileToAudioFileDTO);
    }
}
