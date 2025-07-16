package com.jason.spotifycloneback.repository;

import com.jason.spotifycloneback.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
