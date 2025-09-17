# plataforma-passe-urbano
Plataforma de gerenciamento de cartões de transporte e usuários


O Passe Urbano é uma aplicação web para gerenciamento de usuários e cartões de transporte (comum, estudante e trabalhador).
O sistema permite criar, listar, alterar e ativar/desativar usuários e cartões, além de manter um log de auditoria das ações realizadas no sistema.

A aplicação é construída com Spring Boot para o backend, PostgreSQL como banco de dados e Angular 4 para o frontend.

## Funcionalidades

- Consultar usuários (Lista todos)
- Inclusão de usuários
- Alteração de usuários
- Remoção de Usuários
- Inclusão de cartão a um usuário
- Remoção de cartão de um usuário
- Consulta de cartões (Lista todos)
- Ativação ou Bloqueio do cartão do usuário

## Tecnologias Utilizadas

### Backend
- Java 8
- Spring Boot
- Spring Security + JWT
- PostgreSQL
- Docker

### Frontend
- Angular 4
- Bootstrap 4
- ng2-charts
- JWT-decode

### DevOps
- Docker Compose
- Nginx (para servir frontend em produção local)
- Volumes para persistência do banco de dados

## Pré-Requisitos
- Docker >= 20
- Docker Compose >= 1.29
- Node.js 10.24.1 (Versão usada no projeto)
- NPM 6.14.12 (Versão usada no projeto)

## Gerenciamento de projeto realizado no trello

https://trello.com/b/rBgwAn1m/plataforma-passe-urbano

## Rodando o Projeto Localmente

1. Clone o repositório:
```
git clone https://github.com/MateusHCandido/plataforma-passe-urbano.git
```
2. Inicie os containers via Docker Compose:
```
docker-compose up --build
```

Isso vai criar e subir os serviços: PostgreSQL, backend (usuário, autenticação, gateway, Eureka) e frontend Angular com Nginx.

3. Acesse a aplicação:
- Frontend: http://localhost:4200
- Gateway (API) http://localhost:8765
- Autenticação http://localhost:8000
- Eureka(serviço de descooberta) http://localhost:8761

## Documentação: 

A documentação da API foi moldada utilizando o swagger:
- API do serviço de autenticação: http://localhost:8000/swagger-ui/index.html
- API do serviço de usuário e cartões: http://localhost:8001/swagger-ui/index.html

OBS: O swagger não está configurado para testes. Caso queira testar apenas o backend, pode utilizar o [COLLECTION_POSTMAN](plataforma-passe-urbano.postman_collection.json). Baixe-o e o importe no seu postman

## Observações

- Logs de auditoria registram cada ação realizanda, incluindo o usuário que executou a ação, entidade afetade, id e endpoint
- Permissões:
    - Usuário comum vê apenas os seus próprios logs.
    - Administrador vê logs de todos os usuários
- Senhas iniciais de teste estão criptografadas no banco via BCrypt

```
Login de acesso(ADM)

email: admin@sistema.com
senha: admin123
```
