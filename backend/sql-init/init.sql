CREATE SCHEMA IF NOT EXISTS service;
CREATE SCHEMA IF NOT EXISTS auth;

--Criação da tabela de usuário do cartão
CREATE TABLE IF NOT EXISTS service.usuario_cartao (
    usuario_id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(200) NOT NULL
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

--inicia com usuário no service.usuario_cartao
 INSERT INTO service.usuario_cartao (nome, email, senha)
 VALUES (
     'Mateus',
     'admin@sistema.com',
     '$2a$10$Fu5MvHneyEJdBYi.rmQpjOimSA/CcN351O03PnQf.LCNKTL/Lr03K' -- senha já criptografada
);

-- Criar sequence para numero_cartao
CREATE SEQUENCE IF NOT EXISTS service.seq_cartao_numero START 1000000000 INCREMENT 1;

-- Garantir que a coluna use a sequence (se já existir a coluna, só ajusta o default)
ALTER TABLE service.cartao
    ALTER COLUMN numero_cartao SET DEFAULT nextval('service.seq_cartao_numero');

-- Criação da tabela de permissões
CREATE TABLE auth.roles (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
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
INSERT INTO auth.usuarios (nome, email, senha, role_id, data_criacao)
VALUES (
    'Mateus',
    'admin@sistema.com',
    '$2a$10$Fu5MvHneyEJdBYi.rmQpjOimSA/CcN351O03PnQf.LCNKTL/Lr03K', -- senha já criptografada
    1,
    CURRENT_TIMESTAMP
  );











