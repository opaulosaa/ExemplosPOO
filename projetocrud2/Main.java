package projetocrud2;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repository = new ProdutoRepository();

        // CREATE
        Produto productOne = repository.addProduct("Produto A", 10.0);
        Produto productTwo = repository.addProduct("Produto B", 50.0);

        System.out.println("Produtos adicionados à lista");
        repository.readProducts().forEach(System.out :: println);

        // READ
        System.out.println("\n Buscar produto com ID 1:");
        Produto p = repository.searchByID(5);
        System.out.println(p != null ? p : "Produto nao encontrado.");
        /*
        if(p != null){
        sout(p)
        } else {
        sout (produto nao encontrado)
        }
         */

        // UPDATE
        System.out.println("\n Atualizar produto com o ID 1");
        if (repository.updateProduct(1, "Novo produto A", 102.99)){
            System.out.println("Produto atualizado");
        }
        repository.readProducts().forEach(System.out :: println);

        // DELETE
        System.out.println("\n Remover produto com ID 2: ");
        if (repository.deleteProduct(2)){
            System.out.println("Produto removido");
        }
        repository.readProducts().forEach(System.out :: println);
    }
}
