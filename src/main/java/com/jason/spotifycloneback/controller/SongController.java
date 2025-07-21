package com.jason.spotifycloneback.controller;

import com.jason.spotifycloneback.dto.AudioFileDTO;
import com.jason.spotifycloneback.dto.CreateSongDTO;
import com.jason.spotifycloneback.dto.ReadSongDTO;
import com.jason.spotifycloneback.service.SongService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    private final Validator validator;

    @GetMapping("/songs")
    public ResponseEntity<List<ReadSongDTO>> getAll() {
        return ResponseEntity.ok(songService.getAll());
    }

    @PostMapping(value = "/songs", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReadSongDTO> add(
            @RequestPart(name = "title") String title,
            @RequestPart(name = "artist") String artist,
            @RequestPart(name = "cover") MultipartFile cover,
            @RequestPart(name = "audioFile") MultipartFile audioFile
    ) throws IOException {
        CreateSongDTO createSongDTO = CreateSongDTO.builder()
                .title(title)
                .artist(artist)
                .cover(cover.getBytes())
                .coverContentType(cover.getContentType())
                .audioFile(audioFile.getBytes())
                .audioFileContentType(audioFile.getContentType())
                .build();

        Set<ConstraintViolation<CreateSongDTO>> violations = validator.validate(createSongDTO);
        if (!violations.isEmpty()) {
            String violationsJoined = violations.stream()
                    .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                    .collect(Collectors.joining());
            ProblemDetail validationIssue = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                    "Validation errors for the fields : " + violationsJoined);
            return ResponseEntity.of(validationIssue).build();
        } else {
            return ResponseEntity.ok(songService.create(createSongDTO));
        }
    }

    @GetMapping("/songs/get-content")
    public ResponseEntity<AudioFileDTO> getOneByPublicId(@RequestParam UUID publicId) {
        Optional<AudioFileDTO> songContentByPublicId = songService.getOneByPublicId(publicId);
        return songContentByPublicId.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "UUID Unknown")).build());
    }

    @GetMapping("/songs/search")
    public ResponseEntity<List<ReadSongDTO>> search(@RequestParam String term) {
        return ResponseEntity.ok(songService.search(term));
    }
}
