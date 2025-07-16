package com.jason.spotifycloneback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "audio_file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioFile {

    @Id
    @Column(name = "song_id")
    private Long songId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    private Song song;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;
}
