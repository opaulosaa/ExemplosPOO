package paulosa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Testes de exemplo demonstrando o uso das dependências de teste
 */
@DisplayName("Testes da aplicação Mod37")
class AppTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Deve criar usuário válido")
    void shouldCreateValidUser() {
        // Given
        App.User user = new App.User("João Silva", "joao@email.com", LocalDateTime.now());
        
        // When
        Set<ConstraintViolation<App.User>> violations = validator.validate(user);
        
        // Then
        assertThat(violations).isEmpty();
        assertThat(user.getName()).isEqualTo("João Silva");
        assertThat(user.getEmail()).isEqualTo("joao@email.com");
    }

    @Test
    @DisplayName("Deve rejeitar usuário com nome vazio")
    void shouldRejectUserWithEmptyName() {
        // Given
        App.User user = new App.User("", "joao@email.com", LocalDateTime.now());
        
        // When
        Set<ConstraintViolation<App.User>> violations = validator.validate(user);
        
        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("name"));
    }

    @Test
    @DisplayName("Deve rejeitar usuário com email inválido")
    void shouldRejectUserWithInvalidEmail() {
        // Given
        App.User user = new App.User("João Silva", "email-invalido", LocalDateTime.now());
        
        // When
        Set<ConstraintViolation<App.User>> violations = validator.validate(user);
        
        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("email"));
    }

    @Test
    @DisplayName("Deve rejeitar usuário com nome muito curto")
    void shouldRejectUserWithTooShortName() {
        // Given
        App.User user = new App.User("A", "joao@email.com", LocalDateTime.now());
        
        // When
        Set<ConstraintViolation<App.User>> violations = validator.validate(user);
        
        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("name"));
    }

    @Test
    @DisplayName("Deve testar toString do usuário")
    void shouldTestUserToString() {
        // Given
        LocalDateTime now = LocalDateTime.of(2023, 1, 1, 12, 0);
        App.User user = new App.User("Teste", "teste@email.com", now);
        
        // When
        String result = user.toString();
        
        // Then
        assertThat(result).contains("Teste");
        assertThat(result).contains("teste@email.com");
        assertThat(result).contains("2023-01-01T12:00");
    }

    @Test
    @DisplayName("Deve testar setters e getters")
    void shouldTestSettersAndGetters() {
        // Given
        App.User user = new App.User();
        LocalDateTime now = LocalDateTime.now();
        
        // When
        user.setName("Novo Nome");
        user.setEmail("novo@email.com");
        user.setCreatedAt(now);
        
        // Then
        assertThat(user.getName()).isEqualTo("Novo Nome");
        assertThat(user.getEmail()).isEqualTo("novo@email.com");
        assertThat(user.getCreatedAt()).isEqualTo(now);
    }

    @Test
    @DisplayName("Deve testar construtor com parâmetros")
    void shouldTestConstructorWithParameters() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        String name = "Maria Santos";
        String email = "maria@email.com";
        
        // When
        App.User user = new App.User(name, email, now);
        
        // Then
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getCreatedAt()).isEqualTo(now);
    }

    @Test
    @DisplayName("Deve testar validação de múltiplas violações")
    void shouldTestMultipleValidationViolations() {
        // Given
        App.User user = new App.User("", "email-invalido", LocalDateTime.now());
        
        // When
        Set<ConstraintViolation<App.User>> violations = validator.validate(user);
        
        // Then
        assertThat(violations).hasSizeGreaterThan(1);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("name"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("email"));
    }
}
