package com.example.repository;

import com.example.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Buscar por nome (case insensitive)
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    
    // Buscar produtos com preço menor que um valor
    List<Produto> findByPrecoLessThan(BigDecimal preco);
    
    // Buscar produtos com quantidade maior que zero
    List<Produto> findByQuantidadeGreaterThan(Integer quantidade);
    
    // Buscar produtos por faixa de preço
    @Query("SELECT p FROM Produto p WHERE p.preco BETWEEN :precoMin AND :precoMax")
    List<Produto> findByPrecoBetween(@Param("precoMin") BigDecimal precoMin, 
                                    @Param("precoMax") BigDecimal precoMax);
    
    // Contar produtos por quantidade em estoque
    @Query("SELECT COUNT(p) FROM Produto p WHERE p.quantidade > 0")
    Long countProdutosEmEstoque();
} 