CREATE SCHEMA IF NOT EXISTS service;
CREATE SCHEMA IF NOT EXISTS auth;

--Criação da tabela de usuário do cartão
CREATE TABLE IF NOT EXISTS service.usuario_cartao (
    usuario_id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(200) NOT NULL
);

-- Inserindo usuários no service.usuario_cartao
INSERT INTO service.usuario_cartao (nome, email, senha)
VALUES (
 'Mateus',
 'admin@sistema.com',
 '$2a$10$Fu5MvHneyEJdBYi.rmQpjOimSA/CcN351O03PnQf.LCNKTL/Lr03K'-- senha já criptografada
);

  -- Sincronizar a sequence do BIGSERIAL
SELECT setval(
  'service.usuario_cartao_usuario_id_seq',
  (SELECT COALESCE(MAX(usuario_id), 0) FROM service.usuario_cartao)
);

--Criação da tabela de cartões
CREATE TABLE IF NOT EXISTS service.cartao (
    cartao_id BIGSERIAL PRIMARY KEY,
    numero_cartao BIGINT,
    nome VARCHAR(100),
    tipo_cartao VARCHAR(20),
    status_cartao BOOLEAN,
    usuario_id BIGINT NOT NULL, -- FK direta para usuário
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES service.usuario_cartao(usuario_id)
);

-- Criar sequence para numero_cartao
CREATE SEQUENCE IF NOT EXISTS service.seq_cartao_numero START 1000000000 INCREMENT 1;

-- Garantir que a coluna use a sequence (se já existir a coluna, só ajusta o default)
ALTER TABLE service.cartao
    ALTER COLUMN numero_cartao SET DEFAULT nextval('service.seq_cartao_numero');

-- Ssincroniza a sequence do cartao_id
SELECT setval(
  'service.cartao_cartao_id_seq',
  COALESCE((SELECT MAX(cartao_id) FROM service.cartao), 1)
);

-- Criação da tabela de permissões
CREATE TABLE auth.roles (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE service.audit_log (
    id BIGSERIAL PRIMARY KEY,
    usuario_email VARCHAR(255) NOT NULL,
    acao VARCHAR(100) NOT NULL,
    entidade VARCHAR(50) NOT NULL,
    entidade_id BIGINT,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    detalhe TEXT
);

-- Criação da tabela de usuários com relação 1:N para roles
CREATE TABLE auth.usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(200) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role_id BIGINT,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES auth.roles (id) ON DELETE SET NULL
);

-- Inserindo roles padrão
INSERT INTO auth.roles (id, nome) VALUES
    (1, 'ADMIN'),
    (2, 'USER');

--inicia com usuário com role admin
INSERT INTO auth.usuarios (nome, email, senha, role_id, data_criacao) VALUES
('Mateus Henrique ADM', 'admin@sistema.com','$2a$10$Fu5MvHneyEJdBYi.rmQpjOimSA/CcN351O03PnQf.LCNKTL/Lr03K', 1, CURRENT_TIMESTAMP),
('Mateus Henrique', 'mateus@example.com', '$2a$10$PZqzEug7PUuAp5zvHoLLSu9SzFpI6jYMpM.Nr83J5re5thPYFgV9u', 2, CURRENT_TIMESTAMP),
('João Silva', 'joao@example.com', '$2a$10$PZqzEug7PUuAp5zvHoLLSu9SzFpI6jYMpM.Nr83J5re5thPYFgV9u', 2, CURRENT_TIMESTAMP),
('Maria Oliveira', 'maria@example.com', '$2a$10$PZqzEug7PUuAp5zvHoLLSu9SzFpI6jYMpM.Nr83J5re5thPYFgV9u', 2, CURRENT_TIMESTAMP),
('Ana Souza', 'ana@example.com', '$2a$10$PZqzEug7PUuAp5zvHoLLSu9SzFpI6jYMpM.Nr83J5re5thPYFgV9u', 2, CURRENT_TIMESTAMP),
('Pedro Costa', 'pedro@example.com', '$2a$10$PZqzEug7PUuAp5zvHoLLSu9SzFpI6jYMpM.Nr83J5re5thPYFgV9u', 2, CURRENT_TIMESTAMP);












