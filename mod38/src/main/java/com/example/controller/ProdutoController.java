package com.example.controller;

import com.example.model.Produto;
import com.example.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    // GET - Buscar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodos() {
        List<Produto> produtos = produtoService.buscarTodos();
        return ResponseEntity.ok(produtos);
    }
    
    // GET - Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    // POST - Criar novo produto
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }
    
    // PUT - Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto produtoAtualizado = produtoService.atualizar(id, produto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE - Deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET - Buscar produtos por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }
    
    // GET - Buscar produtos com preço menor que
    @GetMapping("/preco-menor")
    public ResponseEntity<List<Produto>> buscarPorPrecoMenor(@RequestParam BigDecimal preco) {
        List<Produto> produtos = produtoService.buscarPorPrecoMenorQue(preco);
        return ResponseEntity.ok(produtos);
    }
    
    // GET - Buscar produtos em estoque
    @GetMapping("/estoque")
    public ResponseEntity<List<Produto>> buscarEmEstoque() {
        List<Produto> produtos = produtoService.buscarEmEstoque();
        return ResponseEntity.ok(produtos);
    }
    
    // GET - Buscar produtos por faixa de preço
    @GetMapping("/faixa-preco")
    public ResponseEntity<List<Produto>> buscarPorFaixaPreco(
            @RequestParam BigDecimal precoMin, 
            @RequestParam BigDecimal precoMax) {
        List<Produto> produtos = produtoService.buscarPorFaixaPreco(precoMin, precoMax);
        return ResponseEntity.ok(produtos);
    }
    
    // GET - Contar produtos em estoque
    @GetMapping("/contar-estoque")
    public ResponseEntity<Long> contarProdutosEmEstoque() {
        Long quantidade = produtoService.contarProdutosEmEstoque();
        return ResponseEntity.ok(quantidade);
    }
    
    // PATCH - Atualizar quantidade em estoque
    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<Produto> atualizarQuantidade(
            @PathVariable Long id, 
            @RequestParam Integer quantidade) {
        try {
            Produto produto = produtoService.atualizarQuantidade(id, quantidade);
            return ResponseEntity.ok(produto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 