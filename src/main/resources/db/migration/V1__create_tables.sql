CREATE TABLE localizacao
(
    id        BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    latitude  INT                NOT NULL,
    longitude INT                NOT NULL,
    nome      VARCHAR(255)       NOT NULL
);

CREATE TABLE item
(
    id     BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome   VARCHAR(255),
    pontos INT
);

CREATE TABLE inventario
(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE rebelde
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome           VARCHAR(255)       NOT NULL,
    idade          INT,
    genero         VARCHAR(15),
    localizacao_id BIGINT             NOT NULL,
    inventario_id  BIGINT NULL,
    FOREIGN KEY (localizacao_id) REFERENCES localizacao (id),
    FOREIGN KEY (inventario_id) REFERENCES inventario (id)
);

CREATE TABLE inventario_item
(
    inventario_id   BIGINT,
    item_id         BIGINT,
    quantidade_item INT,

    PRIMARY KEY (inventario_id, item_id),
    CONSTRAINT fk_inventario FOREIGN KEY (inventario_id) REFERENCES inventario (id),
    CONSTRAINT fk_item FOREIGN KEY (item_id) REFERENCES item (id)
);