package com.jason.spotifycloneback.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "song")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "song_generator")
    @SequenceGenerator(name = "song_generator", sequenceName = "song_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "public_id", nullable = false)
    private UUID publicId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Lob
    @Column(name = "cover", nullable = false)
    private byte[] cover;

    @Column(name = "cover_content_type", nullable = false)
    private String coverContentType;
}
