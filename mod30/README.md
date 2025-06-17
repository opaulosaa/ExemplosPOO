# Sistema de Vendas

Este é um sistema de vendas desenvolvido em Java que implementa um CRUD completo para Clientes e Produtos.

## Funcionalidades

- Cadastro de Clientes
- Cadastro de Produtos
- Consulta de Clientes e Produtos
- Atualização de dados
- Exclusão de registros

## Tecnologias Utilizadas

- Java
- JDBC
- PostgreSQL
- Maven

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:
- Domain: Classes de domínio (Cliente, Produto)
- DAO: Camada de acesso a dados
- GenericDAO: Implementação genérica do padrão DAO

## Configuração do Banco de Dados

O sistema utiliza PostgreSQL como banco de dados. As tabelas necessárias são:

- TB_CLIENTE
- TB_PRODUTO

## Como Executar

1. Clone o repositório
2. Configure o banco de dados
3. Execute o projeto através do Maven 