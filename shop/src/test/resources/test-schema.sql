DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_information;
DROP TABLE IF EXISTS users;

-- CREATE TABLE address
-- (
--     ID         BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
--     BOOK_TITLE VARCHAR(255)                      NOT NULL,
--     AUTHOR_ID  VARCHAR(255)                      NOT NULL,
--     GENRE_ID   VARCHAR(255)                      NOT NULL
-- );
CREATE TABLE categories
(
    id          SERIAL PRIMARY KEY NOT NULL,
    category    VARCHAR(50)        NOT NULL,
    description VARCHAR(255)       NOT NULL
);
-- CREATE TABLE product
-- (
--     ID         BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
--     GENRE_NAME VARCHAR(255)                      NOT NULL
-- );
-- CREATE TABLE roles
-- (
--     ID      BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
--     book_id VARCHAR(255)                      NOT NULL,
--     comment VARCHAR(555)                      NOT NULL
--
-- );
-- CREATE TABLE user_information
-- (
--     ID      BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
--     book_id VARCHAR(255)                      NOT NULL,
--     comment VARCHAR(555)                      NOT NULL
--
-- );
-- CREATE TABLE users
-- (
--     ID      BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
--     book_id VARCHAR(255)                      NOT NULL,
--     comment VARCHAR(555)                      NOT NULL
--
-- );