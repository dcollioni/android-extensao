package com.collioni.douglas.filmes.data;

/**
 * Created by Douglas on 9/23/2015.
 */
public class FilmeDao {

    private Db4oConnection db4o;

    public FilmeDao(Db4oConnection db4o) {
        this.db4o = db4o;
    }
}
