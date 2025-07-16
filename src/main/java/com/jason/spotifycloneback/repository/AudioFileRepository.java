package com.jason.spotifycloneback.repository;

import com.jason.spotifycloneback.entity.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioFileRepository extends JpaRepository<AudioFile, Long> {
}
