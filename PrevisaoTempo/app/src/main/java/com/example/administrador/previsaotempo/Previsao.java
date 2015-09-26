package com.example.administrador.previsaotempo;

import java.io.Serializable;

/**
 * Created by Administrador on 26/09/2015.
 */
public class Previsao implements Serializable {
    private String data;
    private String descricao;
    private String imagem;
    private int tempMax;
    private int tempMin;
    private int imagemId;

    public Previsao(String data, String descricao, String imagem, int tempMax, int tempMin) {
        this.data = data;
        this.descricao = descricao;
        this.imagem = imagem;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getImagemId() {
        return imagemId;
    }

    public void setImagemId(int imagemId) {
        this.imagemId = imagemId;
    }
}
