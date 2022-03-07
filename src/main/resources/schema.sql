CREATE TABLE product (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    description VARCHAR(128),
    price float,
    supplier VARCHAR(128),
    PRIMARY KEY (id)
);
