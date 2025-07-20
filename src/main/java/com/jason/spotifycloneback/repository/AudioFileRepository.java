package com.jason.spotifycloneback.repository;

import com.jason.spotifycloneback.entity.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AudioFileRepository extends JpaRepository<AudioFile, Long> {

    Optional<AudioFile> findOneBySongPublicId(UUID publicId);
}
