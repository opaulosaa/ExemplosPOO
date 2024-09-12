package com.paulosa;

public class Main {
    public static void main(String[] args) {
        ListaCarro<Object> listaCarro = new ListaCarro<>();
        listaCarro.adicionarCarro(new Honda("Civic"));
        listaCarro.adicionarCarro(new Ferrari("Vermelho"));

        System.out.println("Itens da Lista de Carros: ");
        listaCarro.mostrarCarros();
    }
}
