package paulosa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Aplicação de exemplo demonstrando o uso das dependências adicionadas
 */
public class App {
    
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    static {
        // Configurar Jackson para trabalhar com LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
    }
    
    public static void main(String[] args) {
        logger.info("Iniciando aplicação Mod37");
        
        // Demonstração de logging
        demonstrateLogging();
        
        // Demonstração de JSON processing
        demonstrateJsonProcessing();
        
        // Demonstração de validação
        demonstrateValidation();
        
        // Demonstração de utilitários
        demonstrateUtilities();
        
        logger.info("Aplicação Mod37 finalizada com sucesso!");
    }
    
    private static void demonstrateLogging() {
        logger.debug("Este é um log de debug");
        logger.info("Este é um log de informação");
        logger.warn("Este é um log de aviso");
        logger.error("Este é um log de erro");
    }
    
    private static void demonstrateJsonProcessing() {
        try {
            // Criar um objeto de exemplo
            User user = new User("João Silva", "joao@email.com", LocalDateTime.now());
            
            // Serializar para JSON
            String json = objectMapper.writeValueAsString(user);
            logger.info("JSON serializado: {}", json);
            
            // Deserializar do JSON
            User deserializedUser = objectMapper.readValue(json, User.class);
            logger.info("Usuário deserializado: {}", deserializedUser);
            
        } catch (Exception e) {
            logger.error("Erro ao processar JSON", e);
        }
    }
    
    private static void demonstrateValidation() {
        // Usuário válido
        User validUser = new User("Maria Santos", "maria@email.com", LocalDateTime.now());
        Set<ConstraintViolation<User>> validViolations = validator.validate(validUser);
        
        if (validViolations.isEmpty()) {
            logger.info("Usuário válido: {}", validUser.getName());
        } else {
            logger.warn("Usuário inválido: {}", validViolations);
        }
        
        // Usuário inválido
        User invalidUser = new User("", "email-invalido", LocalDateTime.now());
        Set<ConstraintViolation<User>> invalidViolations = validator.validate(invalidUser);
        
        if (!invalidViolations.isEmpty()) {
            logger.warn("Usuário inválido encontrado:");
            for (ConstraintViolation<User> violation : invalidViolations) {
                logger.warn("  - {}: {}", violation.getPropertyPath(), violation.getMessage());
            }
        }
    }
    
    private static void demonstrateUtilities() {
        // Apache Commons Lang3
        String text = "  hello world  ";
        String trimmed = StringUtils.trim(text);
        String capitalized = StringUtils.capitalize(trimmed);
        logger.info("Texto original: '{}'", text);
        logger.info("Texto processado: '{}'", capitalized);
        
        // Verificar se string está vazia
        boolean isEmpty = StringUtils.isEmpty("");
        boolean isBlank = StringUtils.isBlank("   ");
        logger.info("String vazia: {}", isEmpty);
        logger.info("String em branco: {}", isBlank);
    }
    
    // Classe de exemplo para demonstração
    public static class User {
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        private String name;
        
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        private String email;
        
        private LocalDateTime createdAt;
        
        public User() {}
        
        public User(String name, String email, LocalDateTime createdAt) {
            this.name = name;
            this.email = email;
            this.createdAt = createdAt;
        }
        
        // Getters e Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        
        @Override
        public String toString() {
            return String.format("User{name='%s', email='%s', createdAt=%s}", name, email, createdAt);
        }
    }
}
