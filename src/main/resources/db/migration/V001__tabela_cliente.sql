drop table if exists USUARIO;
drop table if exists CLIENTES;


CREATE TABLE USUARIO
(
    ID         SERIAL  NOT NULL,
    NOME       VARCHAR(60),
    LOGIN      VARCHAR(60) UNIQUE,
    SENHA      VARCHAR(60),
    ROLE       varchar(50),
    PRIMARY KEY (ID)
);

CREATE TABLE CLIENTES
(
    ID               SERIAL NOT NULL,
    NOME             VARCHAR(150) NOT NULL,
    CPF              VARCHAR(20) NOT NULL,
    EMAIL            VARCHAR(150) NOT NULL,
    TELEFONE         VARCHAR(14),
    LOGRADOURO       VARCHAR(60),
    NUMERO           VARCHAR(10),
    COMPLEMENTO      VARCHAR(60),
    BAIRRO           VARCHAR(60),
    CEP              VARCHAR(8),
    NOME_MUNICIPIO   VARCHAR(60),
    UF               CHAR(2),
    NOME_PAIS        VARCHAR(60),
    DATA_NASCIMENTO  TIMESTAMP,
    ID_USUARIO INTEGER NOT NULL,

    PRIMARY KEY (ID),
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID)
);