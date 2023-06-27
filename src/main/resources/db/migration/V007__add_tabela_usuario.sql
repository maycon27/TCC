CREATE TABLE usuario
(
    id         SERIAL  NOT NULL,
    nome       VARCHAR(60),
    login      VARCHAR(60) UNIQUE,
    senha      VARCHAR(60),
    role       varchar(50),
    PRIMARY KEY (id)
);