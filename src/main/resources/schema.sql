CREATE TABLE cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(50) NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         telefone VARCHAR(20),
                         endereco VARCHAR(255),
                         profissao VARCHAR(100)
);
