package com.example.administrador.cidades;

/**
 * Created by Administrador on 18/09/2015.
 */
public class Cidade {
    private String nome;
    private int populacao;
    private int foto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Cidade(String nome, int populacao, int foto) {
        this.nome = nome;
        this.populacao = populacao;
        this.foto = foto;
    }

    @Override
    public String toString() {
        return nome;
    }
}
