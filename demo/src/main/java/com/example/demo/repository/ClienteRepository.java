package com.example.demo.repository;

import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar por CPF
    Optional<Cliente> findByCpf(String cpf);

    // Buscar por email
    Optional<Cliente> findByEmail(String email);

    // Buscar por nome (contendo)
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    // Buscar por status
    List<Cliente> findByStatus(Cliente.StatusCliente status);

    // Verificar se CPF existe
    boolean existsByCpf(String cpf);

    // Verificar se email existe
    boolean existsByEmail(String email);

    // Busca personalizada por nome ou email
    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:termo% OR c.email LIKE %:termo%")
    List<Cliente> buscarPorNomeOuEmail(@Param("termo") String termo);

    // Buscar clientes ativos
    @Query("SELECT c FROM Cliente c WHERE c.status = 'ATIVO' ORDER BY c.nome")
    List<Cliente> buscarClientesAtivos();
} 