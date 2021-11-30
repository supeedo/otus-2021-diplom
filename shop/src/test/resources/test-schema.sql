DROP TABLE IF EXISTS user_informations;
DROP TABLE IF EXISTS user_address;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS status_order CASCADE;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS order_items;



CREATE TABLE user_address
(
    id               SERIAL PRIMARY KEY NOT NULL,
    city             VARCHAR(50)        NOT NULL,
    street           VARCHAR(50)        NOT NULL,
    house_number     VARCHAR(50)        NOT NULL,
    floor            SMALLINT,
    apartment_number VARCHAR(50)
);
CREATE TABLE category
(
    id          SERIAL PRIMARY KEY NOT NULL,
    category    VARCHAR(50)        NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY NOT NULL,
    role VARCHAR(50)        NOT NULL UNIQUE

);
CREATE TABLE status_orders
(
    id     SERIAL PRIMARY KEY NOT NULL,
    status VARCHAR(100)       NOT NULL UNIQUE
);
CREATE TABLE user_informations
(
    id              SERIAL PRIMARY KEY NOT NULL,
    first_name      VARCHAR(50)        NOT NULL,
    last_name       VARCHAR(50)        NOT NULL,
    patronymic      VARCHAR(50),
    phone           VARCHAR(50)        NOT NULL UNIQUE,
    user_address_id BIGINT references user_address (id)
);
CREATE TABLE users
(
    id           SERIAL PRIMARY KEY NOT NULL,
    email        VARCHAR(50)        NOT NULL UNIQUE,
    password     VARCHAR(1000)        NOT NULL,
    user_info_id BIGINT,
    role_id      BIGINT references roles (id),
    active       BOOLEAN            NOT NULL

);
CREATE TABLE products
(
    id          SERIAL PRIMARY KEY NOT NULL,
    category_id BIGINT             NOT NULL,
    name        VARCHAR(100)       NOT NULL UNIQUE,
    description VARCHAR(2048),
    price       DOUBLE PRECISION   NOT NULL,
    active      BOOLEAN            NOT NULL
);

CREATE TABLE orders
(
    id        SERIAL PRIMARY KEY NOT NULL,
    user_id   BIGINT             NOT NULL,
    note      VARCHAR(2048),
    status_id BIGINT             NOT NULL references status_orders (id)
);

CREATE TABLE order_items
(
    id            SERIAL PRIMARY KEY NOT NULL,
    order_id      BIGINT             references orders (id),
    product_id    BIGINT             NOT NULL references products (id),
    product_count BIGINT             NOT NULL
);




