# Projeto Monolítico Web - Gerenciamento de Produtos

Este é um projeto monolítico web simples desenvolvido com Spring Boot, implementando um sistema de gerenciamento de produtos com arquitetura em camadas.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Lombok** (redução de boilerplate)
- **Maven** (gerenciamento de dependências)

## Arquitetura

O projeto segue uma arquitetura em camadas:

```
src/main/java/com/example/
├── MonolitoWebApplication.java    # Classe principal da aplicação
├── model/
│   └── Produto.java              # Entidade JPA
├── repository/
│   └── ProdutoRepository.java    # Interface de acesso a dados
├── service/
│   └── ProdutoService.java       # Camada de lógica de negócio
└── controller/
    └── ProdutoController.java    # Controlador REST
```

## Funcionalidades

### CRUD de Produtos
- ✅ Criar produto
- ✅ Buscar todos os produtos
- ✅ Buscar produto por ID
- ✅ Atualizar produto
- ✅ Deletar produto

### Consultas Específicas
- ✅ Buscar produtos por nome (case insensitive)
- ✅ Buscar produtos com preço menor que um valor
- ✅ Buscar produtos em estoque (quantidade > 0)
- ✅ Buscar produtos por faixa de preço
- ✅ Contar produtos em estoque
- ✅ Atualizar quantidade em estoque

## Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior

### Passos para execução

1. **Clone ou navegue até o diretório do projeto:**
   ```bash
   cd mod38
   ```

2. **Compile e execute o projeto:**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse a aplicação:**
   - API REST: http://localhost:8080/api/produtos
   - Console H2: http://localhost:8080/h2-console

### Configurações do H2 Console
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** `password`

## Endpoints da API

### Produtos
- `GET /api/produtos` - Listar todos os produtos
- `GET /api/produtos/{id}` - Buscar produto por ID
- `POST /api/produtos` - Criar novo produto
- `PUT /api/produtos/{id}` - Atualizar produto
- `DELETE /api/produtos/{id}` - Deletar produto

### Consultas Específicas
- `GET /api/produtos/buscar?nome={nome}` - Buscar por nome
- `GET /api/produtos/preco-menor?preco={valor}` - Produtos com preço menor
- `GET /api/produtos/estoque` - Produtos em estoque
- `GET /api/produtos/faixa-preco?precoMin={min}&precoMax={max}` - Por faixa de preço
- `GET /api/produtos/contar-estoque` - Contar produtos em estoque
- `PATCH /api/produtos/{id}/quantidade?quantidade={qtd}` - Atualizar quantidade

## Exemplo de Uso

### Criar um produto
```bash
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Novo Produto",
    "descricao": "Descrição do produto",
    "preco": 99.99,
    "quantidade": 10
  }'
```

### Buscar todos os produtos
```bash
curl http://localhost:8080/api/produtos
```

### Buscar produtos em estoque
```bash
curl http://localhost:8080/api/produtos/estoque
```

## Estrutura do Banco de Dados

A tabela `produtos` possui os seguintes campos:
- `id` (Long, Primary Key)
- `nome` (String, obrigatório)
- `descricao` (String, opcional)
- `preco` (BigDecimal, obrigatório)
- `quantidade` (Integer, obrigatório)
- `data_criacao` (LocalDateTime, automático)
- `data_atualizacao` (LocalDateTime, automático)

## Dados Iniciais

O projeto já vem com 8 produtos de exemplo pré-cadastrados, incluindo:
- Notebook Dell Inspiron
- Mouse Logitech
- Teclado Mecânico
- Monitor LG 24"
- Headset Gamer
- Webcam HD
- Pendrive 32GB
- SSD 500GB

## Logs e Debug

O projeto está configurado para mostrar:
- Logs SQL do Hibernate
- Logs de requisições HTTP
- Logs de debug da aplicação

## Próximos Passos

Para expandir o projeto, você pode:
1. Adicionar validações com Bean Validation
2. Implementar autenticação e autorização
3. Adicionar testes unitários e de integração
4. Implementar paginação nas consultas
5. Adicionar documentação com Swagger/OpenAPI
6. Implementar cache com Redis
7. Adicionar upload de imagens para produtos 