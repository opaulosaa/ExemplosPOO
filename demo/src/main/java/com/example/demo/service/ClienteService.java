package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Salvar novo cliente
    public Cliente salvar(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    // Atualizar cliente existente
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clienteExistente = buscarPorId(id);
        
        // Verificar se CPF foi alterado e se já existe
        if (!clienteExistente.getCpf().equals(cliente.getCpf()) && 
            clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado para outro cliente");
        }
        
        // Verificar se email foi alterado e se já existe
        if (!clienteExistente.getEmail().equals(cliente.getEmail()) && 
            clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Email já cadastrado para outro cliente");
        }
        
        // Atualizar campos
        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setCelular(cliente.getCelular());
        clienteExistente.setEndereco(cliente.getEndereco());
        clienteExistente.setCidade(cliente.getCidade());
        clienteExistente.setEstado(cliente.getEstado());
        clienteExistente.setCep(cliente.getCep());
        clienteExistente.setObservacoes(cliente.getObservacoes());
        clienteExistente.setStatus(cliente.getStatus());
        
        return clienteRepository.save(clienteExistente);
    }

    // Buscar por ID
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

    // Buscar por CPF
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    // Buscar por email
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    // Listar todos
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Listar clientes ativos
    @Transactional(readOnly = true)
    public List<Cliente> listarClientesAtivos() {
        return clienteRepository.buscarClientesAtivos();
    }

    // Buscar por nome
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Buscar por termo (nome ou email)
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorTermo(String termo) {
        return clienteRepository.buscarPorNomeOuEmail(termo);
    }

    // Alterar status do cliente
    public Cliente alterarStatus(Long id, Cliente.StatusCliente status) {
        Cliente cliente = buscarPorId(id);
        cliente.setStatus(status);
        return clienteRepository.save(cliente);
    }

    // Deletar cliente (soft delete - apenas inativa)
    public void deletar(Long id) {
        Cliente cliente = buscarPorId(id);
        cliente.setStatus(Cliente.StatusCliente.INATIVO);
        clienteRepository.save(cliente);
    }

    // Validações
    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório");
        }
        
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório");
        }
        
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email é obrigatório");
        }
        
        // Validar formato do CPF (apenas números, 11 dígitos)
        String cpfLimpo = cliente.getCpf().replaceAll("[^0-9]", "");
        if (cpfLimpo.length() != 11) {
            throw new RuntimeException("CPF deve conter 11 dígitos");
        }
        
        // Validar formato do email
        if (!cliente.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Email inválido");
        }
        
        // Verificar se CPF já existe
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        
        // Verificar se email já existe
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
    }
} 