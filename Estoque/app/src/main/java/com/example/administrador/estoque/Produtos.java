package com.example.administrador.estoque;

import java.io.Serializable;

/**
 * Created by Administrador on 19/09/2015.
 */
public class Produtos implements Serializable {
    private int codigo;
    private String nome;
    private double preco;

    public Produtos(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome;
    }
}


