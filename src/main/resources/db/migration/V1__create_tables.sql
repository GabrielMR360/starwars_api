CREATE TABLE localizacao
(
    id        BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    latitude  INT                NOT NULL,
    longitude INT                NOT NULL,
    nome      VARCHAR(255)       NOT NULL
);

CREATE TABLE item
(
    id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome       VARCHAR(255),
    quantidade INT,
    pontos     INT
);

CREATE TABLE inventario
(
    id      BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    item_id BIGINT             NOT NULL
);

CREATE TABLE rebelde
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome           VARCHAR(255)       NOT NULL,
    idade          INT,
    genero         VARCHAR(15),
    localizacao_id BIGINT             NOT NULL,
    inventario_id  BIGINT             NOT NULL,
    FOREIGN KEY (localizacao_id) REFERENCES localizacao (id),
    FOREIGN KEY (inventario_id) REFERENCES inventario (id)
);

CREATE TABLE inventario_item
(
    inventario_id BIGINT,
    item_id       BIGINT
);