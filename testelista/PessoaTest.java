package testelista;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaTest {
    @Test
    public void testMulheresNaLista() {

     List<Pessoa> pessoas = new ArrayList<>();
    Pessoa p1 = new Pessoa("Goku", 'M');
    Pessoa p2 = new Pessoa("Vegeta", 'M');
    Pessoa p3 = new Pessoa("Gohan", 'M');
    Pessoa p4 = new Pessoa("Bulma", 'F');
    Pessoa p5 = new Pessoa("Videl", 'F');
    Pessoa p6 = new Pessoa("Chi Chi", 'F');

    pessoas.add(p1);
    pessoas.add(p2);
    pessoas.add(p3);
    pessoas.add(p4);
    pessoas.add(p5);
    pessoas.add(p6);


        List<String> mulheres = pessoas.stream()
                .filter(pessoa -> pessoa.getSexo() == 'F')
                .map(Pessoa::getNome)
                .collect(Collectors.toList());

        boolean todasMulheres = pessoas.stream()
                .filter(pessoa -> mulheres.contains(pessoa.getNome()))
                .allMatch(pessoa -> pessoa.getSexo() == 'F');
        Assert.assertTrue("Existem pessoas que não são mulheres na lista.", todasMulheres);

    }

}
