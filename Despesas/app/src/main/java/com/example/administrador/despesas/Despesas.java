package com.example.administrador.despesas;

/**
 * Created by Administrador on 19/09/2015.
 */
public class Despesas {

    private String descricao;
    private double valor;

    public Despesas(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
