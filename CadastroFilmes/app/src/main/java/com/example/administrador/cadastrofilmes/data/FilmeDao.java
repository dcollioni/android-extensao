package com.example.administrador.cadastrofilmes.data;

/**
 * Created by Administrador on 25/09/2015.
 */
public class FilmeDao {
    private Db4oConnection db4o;

    public FilmeDao(Db4oConnection db4o) {
        this.db4o = db4o;
    }
}
