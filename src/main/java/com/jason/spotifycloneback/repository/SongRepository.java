package com.jason.spotifycloneback.repository;

import com.jason.spotifycloneback.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s WHERE lower(s.title) LIKE lower(concat('%',:searchTerm,'%'))" +
            "OR lower(s.artist) LIKE lower(concat('%',:searchTerm,'%'))")
    List<Song> findByTitleOrArtistContaining(String searchTerm);
}
