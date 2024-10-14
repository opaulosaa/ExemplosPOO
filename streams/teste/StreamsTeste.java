package com.paulosa.streams.teste;

import com.paulosa.streams.dominio.Pessoas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsTeste {
    private static List <Pessoas> pessoas = new ArrayList<>(List.of(
            new Pessoas("Goku", 'M'),
            new Pessoas("Vegeta", 'M'),
            new Pessoas("Videl", 'F'),
            new Pessoas("Bulma", 'F')
    ));
    public static void main(String[] args) {
        pessoas.sort(Comparator.comparing(Pessoas::getSexo));
        List<String> mulheres = pessoas.stream()

                .filter(pessoa -> pessoa.getSexo() == 'F')

                .map(Pessoas::getNome)

                .collect(Collectors.toList());
        System.out.println(pessoas);
        System.out.println("----------------");
        System.out.println(mulheres);
    }
}
