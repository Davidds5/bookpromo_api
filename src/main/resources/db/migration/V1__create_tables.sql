CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(255)
);

CREATE TABLE promotions (
    id BIGSERIAL PRIMARY KEY,
    platform VARCHAR(255) NOT NULL,
    price NUMERIC(38, 2) NOT NULL,
    affiliate_link VARCHAR(255) NOT NULL,
    book_id BIGINT,
    CONSTRAINT fk_promotion_book FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE price_alerts (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT,
    user_id BIGINT,
    desired_price NUMERIC(38, 2) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_price_alert_book FOREIGN KEY (book_id) REFERENCES books (id),
    CONSTRAINT fk_price_alert_user FOREIGN KEY (user_id) REFERENCES users (id)
);
