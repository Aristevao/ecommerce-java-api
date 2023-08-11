CREATE TABLE IF NOT EXISTS product (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    description VARCHAR(128),
    price float,
    supplier VARCHAR(128),
    path VARCHAR(255),
    PRIMARY KEY (id)
);
