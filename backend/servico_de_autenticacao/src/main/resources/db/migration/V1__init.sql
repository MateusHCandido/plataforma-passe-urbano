-- Criação da tabela de permissões
CREATE TABLE roles (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- Criação da tabela de usuários com relação 1:N para roles
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(200) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role_id BIGINT,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE SET NULL
);

-- Inserindo roles padrão
INSERT INTO roles (id, nome) VALUES
    (1, 'ADMIN'),
    (2, 'USER');


