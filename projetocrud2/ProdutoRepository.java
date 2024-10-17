package projetocrud2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {
    private List<Produto> products = new ArrayList<>();
    private int idCounter = 1;

    // CREATE - add um novo produto
    public Produto addProduct(String name, double price){
        Produto product = new Produto(idCounter++, name, price);
        products.add(product);
        return product;
    }
    // READ - Retorna (lê) todos os produtos
    public List<Produto> readProducts(){
        return products;
    }
    // READ - Busca produto por ID
    public Produto searchByID(int id){
        Optional<Produto> product = products.stream().filter(p ->p.getId() == id).findFirst();
        return product.orElse(null);
    }
    // UPDATE - atualiza produto existente
    public boolean updateProduct(int id, String name, double price){
        Produto product = searchByID(id);
        if (product != null){
            product.setName(name);
            product.setPrice(price);
            return true;
        }
        return false;
    }
    // DELETE - remove um produto pelo ID
    public boolean deleteProduct(int id){
        return products.removeIf(p -> p.getId() == id);
    }
}
