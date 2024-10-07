package com.paulosa.reflections;

import com.paulosa.anotations.Tabela;

    @com.paulosa.anotations.Tabela(nome = "Tabela 1", numero = 18)
    public class AnnotationEx {
        public static void main(String[] args) {
            Class<?> classe = com.paulosa.anotations.AnnotationEx.class;

            com.paulosa.anotations.Tabela annotation = classe.getAnnotation(Tabela.class);
            System.out.println("Nome da anotação: " + annotation.annotationType().getSimpleName());
            System.out.println("Valor do atributo nome: " + annotation.nome());
            System.out.println("Valor do atributo numero: " + annotation.numero());
        }
    }

