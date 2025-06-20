# Mod37 - Projeto Java com Dependências Modernas

Este projeto demonstra o uso de várias dependências modernas e populares no ecossistema Java.

## Dependências Adicionadas

### 🧪 Testes
- **JUnit 4** (4.13.2) - Framework de testes tradicional
- **JUnit 5** (5.9.2) - Framework de testes moderno com suporte a lambdas
- **Mockito** (5.1.1) - Framework para criação de mocks em testes
- **AssertJ** (3.24.2) - Biblioteca para assertions mais expressivas

### 📝 Logging
- **SLF4J** (1.7.36) - API de logging
- **Logback** (1.2.12) - Implementação de logging com configuração flexível

### 🔄 JSON Processing
- **Jackson Core** (2.14.2) - Biblioteca para processamento JSON
- **Jackson JSR310** (2.14.2) - Suporte para tipos de data/hora do Java 8+

### 🌐 HTTP Client
- **Apache HttpClient** (4.5.14) - Cliente HTTP para requisições web

### 🛠️ Utilitários
- **Apache Commons Lang3** (3.12.0) - Utilitários para manipulação de strings e objetos
- **Apache Commons IO** (2.11.0) - Utilitários para operações de I/O
- **Google Guava** (31.1-jre) - Biblioteca de utilitários do Google

### ✅ Validação
- **Bean Validation API** (2.0.1.Final) - API para validação de beans
- **Hibernate Validator** (6.2.5.Final) - Implementação da Bean Validation

## Como Usar

### Executar a Aplicação
```bash
mvn clean compile exec:java -Dexec.mainClass="paulosa.App"
```

### Executar os Testes
```bash
mvn test
```

### Compilar o Projeto
```bash
mvn clean compile
```

## Exemplos de Uso

### Logging
```java
private static final Logger logger = LoggerFactory.getLogger(App.class);
logger.info("Mensagem de informação");
logger.error("Erro ocorreu", exception);
```

### JSON Processing
```java
ObjectMapper mapper = new ObjectMapper();
String json = mapper.writeValueAsString(object);
MyClass obj = mapper.readValue(json, MyClass.class);
```

### Validação
```java
@NotBlank(message = "Nome é obrigatório")
@Email(message = "Email deve ser válido")
private String email;

Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
Set<ConstraintViolation<User>> violations = validator.validate(user);
```

### Utilitários
```java
// Apache Commons Lang3
String trimmed = StringUtils.trim("  texto  ");
boolean isEmpty = StringUtils.isEmpty("");

// Apache Commons IO
String content = FileUtils.readFileToString(file, "UTF-8");
```

### Testes com Mockito
```java
@Mock
private UserService userService;

@Test
void testWithMock() {
    when(userService.findUser(1L)).thenReturn(new User("João"));
    User user = userService.findUser(1L);
    assertThat(user.getName()).isEqualTo("João");
}
```

## Configuração

### Logback
O arquivo `src/main/resources/logback.xml` configura:
- Logs no console
- Logs em arquivo com rotação diária
- Nível de log DEBUG para o pacote `paulosa`

### Java Version
O projeto está configurado para usar Java 11.

## Estrutura do Projeto
```
mod37/
├── src/
│   ├── main/
│   │   ├── java/paulosa/
│   │   │   └── App.java
│   │   └── resources/
│   │       └── logback.xml
│   └── test/
│       └── java/paulosa/
│           └── AppTest.java
├── pom.xml
└── README.md
```

## Próximos Passos

Algumas sugestões para expandir o projeto:

1. **Spring Boot** - Para desenvolvimento de aplicações web
2. **JPA/Hibernate** - Para persistência de dados
3. **Spring Security** - Para autenticação e autorização
4. **Swagger/OpenAPI** - Para documentação de APIs
5. **Docker** - Para containerização
6. **JUnit 5 Extensions** - Para testes mais avançados
7. **Testcontainers** - Para testes de integração com banco de dados 