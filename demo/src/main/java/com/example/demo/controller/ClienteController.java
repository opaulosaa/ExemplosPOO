package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    // POST - Cadastrar novo cliente
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        try {
            Cliente clienteSalvo = clienteService.salvar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET - Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Buscar cliente por CPF
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Buscar cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarPorEmail(@PathVariable String email) {
        return clienteService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    // GET - Listar apenas clientes ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<Cliente>> listarAtivos() {
        List<Cliente> clientes = clienteService.listarClientesAtivos();
        return ResponseEntity.ok(clientes);
    }

    // GET - Buscar clientes por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam String nome) {
        List<Cliente> clientes = clienteService.buscarPorNome(nome);
        return ResponseEntity.ok(clientes);
    }

    // GET - Buscar por termo (nome ou email)
    @GetMapping("/buscar-termo")
    public ResponseEntity<List<Cliente>> buscarPorTermo(@RequestParam String termo) {
        List<Cliente> clientes = clienteService.buscarPorTermo(termo);
        return ResponseEntity.ok(clientes);
    }

    // PUT - Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteAtualizado = clienteService.atualizar(id, cliente);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PATCH - Alterar status do cliente
    @PatchMapping("/{id}/status")
    public ResponseEntity<Cliente> alterarStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Cliente.StatusCliente statusCliente = Cliente.StatusCliente.valueOf(status.toUpperCase());
            Cliente cliente = clienteService.alterarStatus(id, statusCliente);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Deletar cliente (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            clienteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Verificar se CPF existe
    @GetMapping("/verificar-cpf/{cpf}")
    public ResponseEntity<Boolean> verificarCpf(@PathVariable String cpf) {
        boolean existe = clienteService.buscarPorCpf(cpf).isPresent();
        return ResponseEntity.ok(existe);
    }

    // GET - Verificar se email existe
    @GetMapping("/verificar-email/{email}")
    public ResponseEntity<Boolean> verificarEmail(@PathVariable String email) {
        boolean existe = clienteService.buscarPorEmail(email).isPresent();
        return ResponseEntity.ok(existe);
    }
} 