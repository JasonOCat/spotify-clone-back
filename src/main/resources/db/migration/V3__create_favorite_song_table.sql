CREATE TABLE favorite_song (
   user_email VARCHAR(255) NOT NULL,
   song_public_id UUID NOT NULL
);

ALTER TABLE favorite_song
ADD CONSTRAINT pk_user_songs PRIMARY KEY (user_email, song_public_id);

CREATE UNIQUE INDEX IX_favorite_songPK
ON favorite_song (song_public_id, user_email);

ALTER TABLE favorite_song
ADD CONSTRAINT fk_favorite_song_id FOREIGN KEY (song_public_id)
REFERENCES song (public_id);