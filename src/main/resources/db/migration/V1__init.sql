-- Schema
CREATE SCHEMA IF NOT EXISTS spotify_clone;

-- Sequences
CREATE SEQUENCE IF NOT EXISTS song_generator START 1 INCREMENT 1;

-- Table song
CREATE TABLE IF NOT EXISTS song (
    id BIGINT PRIMARY KEY NOT NULL,
    public_id UUID NOT NULL UNIQUE,
    title VARCHAR(256) NOT NULL,
    artist VARCHAR(256) NOT NULL,
    cover OID NOT NULL,
    cover_content_type VARCHAR(255) NOT NULL
    );

-- Table audio_file
CREATE TABLE IF NOT EXISTS audio_file (
      song_id BIGINT PRIMARY KEY NOT NULL,
      file OID NOT NULL,
      file_content_type VARCHAR(255) NOT NULL
);