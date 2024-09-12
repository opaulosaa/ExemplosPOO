package com.paulosa;

import java.util.ArrayList;
import java.util.List;

public class ListaCarro<T>{
    private List<T> carros;

    public ListaCarro(){
        this.carros = new ArrayList<>();
    }
    public void adicionarCarro(T carro){
        carros.add(carro);
    }

    public void mostrarCarros(){
        for (T carro : carros){
            System.out.println(carro);
        }
    }
}
