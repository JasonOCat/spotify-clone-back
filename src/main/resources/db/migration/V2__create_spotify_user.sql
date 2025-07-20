CREATE SEQUENCE IF NOT EXISTS user_generator START 1 INCREMENT 1;

CREATE TABLE spotify_user (
    id BIGINT PRIMARY KEY NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(255),
    image_url VARCHAR(256),
    subscription VARCHAR(256),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    CONSTRAINT ux_user_email UNIQUE (email)
);