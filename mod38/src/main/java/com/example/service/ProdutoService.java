package com.example.service;

import com.example.model.Produto;
import com.example.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    // Buscar todos os produtos
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }
    
    // Buscar produto por ID
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
    
    // Salvar produto
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    // Atualizar produto
    public Produto atualizar(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            return produtoRepository.save(produto);
        }
        throw new RuntimeException("Produto não encontrado com ID: " + id);
    }
    
    // Deletar produto
    public void deletar(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
    }
    
    // Buscar produtos por nome
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    // Buscar produtos com preço menor que
    public List<Produto> buscarPorPrecoMenorQue(BigDecimal preco) {
        return produtoRepository.findByPrecoLessThan(preco);
    }
    
    // Buscar produtos em estoque
    public List<Produto> buscarEmEstoque() {
        return produtoRepository.findByQuantidadeGreaterThan(0);
    }
    
    // Buscar produtos por faixa de preço
    public List<Produto> buscarPorFaixaPreco(BigDecimal precoMin, BigDecimal precoMax) {
        return produtoRepository.findByPrecoBetween(precoMin, precoMax);
    }
    
    // Contar produtos em estoque
    public Long contarProdutosEmEstoque() {
        return produtoRepository.countProdutosEmEstoque();
    }
    
    // Atualizar quantidade em estoque
    public Produto atualizarQuantidade(Long id, Integer novaQuantidade) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            produto.setQuantidade(novaQuantidade);
            return produtoRepository.save(produto);
        }
        throw new RuntimeException("Produto não encontrado com ID: " + id);
    }
} 