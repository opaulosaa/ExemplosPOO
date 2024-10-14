package com.paulosa.streams.teste;

import com.paulosa.streams.dominio.Pessoas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StreamsTeste {
    private static List <Pessoas> pessoas = new ArrayList<>(List.of(
            new Pessoas("Goku", 'M'),
            new Pessoas("Vegeta", 'M'),
            new Pessoas("Videl", 'F'),
            new Pessoas("Bulma", 'F')
    ));
    public static void main(String[] args) {
        pessoas.sort(Comparator.comparing(Pessoas::getSexo));
        List<String> mulheres = new ArrayList<>();
        for (Pessoas pessoa : pessoas){
            if (pessoa.getSexo() == 'F'){
                mulheres.add(pessoa.getNome());
            }
        }
        System.out.println(pessoas);
        System.out.println("----------------");
        System.out.println(mulheres);
    }
}
