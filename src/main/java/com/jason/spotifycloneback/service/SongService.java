package com.jason.spotifycloneback.service;

import com.jason.spotifycloneback.dto.*;
import com.jason.spotifycloneback.entity.AudioFile;
import com.jason.spotifycloneback.entity.Favorite;
import com.jason.spotifycloneback.entity.FavoriteId;
import com.jason.spotifycloneback.entity.Song;
import com.jason.spotifycloneback.mapper.AudioFileMapper;
import com.jason.spotifycloneback.mapper.SongMapper;
import com.jason.spotifycloneback.repository.AudioFileRepository;
import com.jason.spotifycloneback.repository.FavoriteRepository;
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

    private final UserService userService;

    private final FavoriteRepository favoriteRepository;

    @Transactional(readOnly = true)
    public List<ReadSongDTO> getAll() {
        List<ReadSongDTO> allSongs = songRepository.findAll()
                .stream()
                .map(songMapper::songToReadSongDTO)
                .toList();

        if (userService.isAuthenticated()) {
            return fetchFavoritesStatusForSongs(allSongs);
        }

        return allSongs;
    }

    public ReadSongDTO create(CreateSongDTO createSongDTO) {
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

    public List<ReadSongDTO> search(String searchTerm) {
        List<ReadSongDTO> searchedSongs = songRepository.findByTitleOrArtistContaining(searchTerm)
                .stream()
                .map(songMapper::songToReadSongDTO)
                .toList();

        if (userService.isAuthenticated()) {
            return fetchFavoritesStatusForSongs(searchedSongs);
        } else {
            return searchedSongs;
        }
    }

    public State<FavoriteSongDTO, String> addOrRemoveFromFavorite(FavoriteSongDTO favoriteSongDTO, String email) {
        Optional<Song> songToLikeOpt = songRepository.findOneByPublicId(favoriteSongDTO.publicId());
        if (songToLikeOpt.isEmpty()) {
            return State.onError("Song public id doesn't exist");
        }

        Song songToLike = songToLikeOpt.get();

        ReadUserDTO userWhoLikedSong = userService.getByEmail(email).orElseThrow();

        if (favoriteSongDTO.isFavorite()) {
            Favorite favorite = new Favorite();
            favorite.setSongPublicId(songToLike.getPublicId());
            favorite.setUserEmail(userWhoLikedSong.email());
            favoriteRepository.save(favorite);
        } else {
            FavoriteId favoriteId = new FavoriteId(songToLike.getPublicId(), userWhoLikedSong.email());
            favoriteRepository.deleteById(favoriteId);
            favoriteSongDTO = new FavoriteSongDTO(false, songToLike.getPublicId());
        }

        return State.onSuccess(favoriteSongDTO);
    }

    public List<ReadSongDTO> fetchFavoriteSongs(String email) {
        return songRepository.findAllFavoriteByUserEmail(email)
                .stream()
                .map(songMapper::songToReadSongDTO)
                .toList();
    }

    private List<ReadSongDTO> fetchFavoritesStatusForSongs(List<ReadSongDTO> songs) {
        ReadUserDTO authenticatedUser = userService.getAuthenticatedUserFromSecurityContext();

        List<UUID> songPublicIds = songs.stream().map(ReadSongDTO::publicId).toList();

        List<UUID> userFavoriteSongs = favoriteRepository.findAllByUserEmailAndSongPublicIdIn(authenticatedUser.email(), songPublicIds)
                .stream()
                .map(Favorite::getSongPublicId)
                .toList();

        return songs.stream()
                .map(song ->
                        userFavoriteSongs.contains(song.publicId()) ?
                                song.withFavorite(true)
                                : song
                )
                .toList();
    }
}
