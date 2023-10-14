drop table if exists PRODUTO;
CREATE TABLE PRODUTO
(
    ID  SERIAL NOT NULL,
    NOME VARCHAR(250) NOT NULL,
    QUANTIDADE_ESTOQUE INTEGER,
    PRECO DECIMAL(18,6) NOT NULL,
    SKU VARCHAR(50),
    MARCA VARCHAR(250),
    DESCRICAO VARCHAR(250),
    UNIDADE_MEDIDA VARCHAR(5),
    ATIVO VARCHAR(1),
    SITUACAO VARCHAR(1),
    ID_CATEGORIA_PRODUTO INTEGER NOT NULL,
    IMAGEM_PRODUTO VARCHAR(250),
    ID_ESTABELECIMENTO INTEGER NOT NULL,

    PRIMARY KEY (ID),
    FOREIGN KEY (ID_CATEGORIA_PRODUTO) REFERENCES CATEGORIA_PRODUTO(ID)
);