DROP TABLE IF EXISTS user_address;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_information;
DROP TABLE IF EXISTS users;

CREATE TABLE user_address
(
    id               SERIAL PRIMARY KEY NOT NULL,
    city             VARCHAR(50)        NOT NULL,
    street           VARCHAR(50)        NOT NULL,
    house_number     VARCHAR(50)        NOT NULL,
    floor            SMALLINT,
    apartment_number VARCHAR(50)
);
CREATE TABLE categories
(
    id          SERIAL PRIMARY KEY NOT NULL,
    category    VARCHAR(50)        NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY NOT NULL,
    role VARCHAR(50)        NOT NULL

);
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
CREATE TABLE product
(
    ID          SERIAL PRIMARY KEY NOT NULL,
    category_id BIGINT             NOT NULL,
    name        VARCHAR(100)       NOT NULL,
    description VARCHAR(2048),
    price       DOUBLE PRECISION   NOT NULL,
    active      BOOLEAN            NOT NULL
);

CREATE TABLE orders
(
    id            SERIAL PRIMARY KEY NOT NULL,
    create_time   DATE               NOT NULL,
    delivery_time DATE               NOT NULL,
    user_id       BIGINT             NOT NULL,
    note          VARCHAR(2048),
    status        VARCHAR(100)       NOT NULL
);

CREATE TABLE product_orders
(
    id            SERIAL PRIMARY KEY             NOT NULL,
    order_id      BIGINT references orders (id)  NOT NULL,
    product_id    BIGINT references product (id) NOT NULL,
    product_count BIGINT                         NOT NULL
);