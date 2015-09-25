package com.collioni.douglas.filmes.data;

/**
 * Created by Douglas on 9/23/2015.
 */
public class Db4oConnection {
    private final String DB4O_FILE = "filmes.db4o";

    private String dir;
//    private ObjectContainer db;

    public Db4oConnection(String dir) {
        this.dir = dir;
    }

//    public ObjectContainer db() {
//        return db;
//    }

    public void open() {
//        String dbFile = dir + DB4O_FILE;
//        db = Db4oEmbedded.openFile(
//                Db4oEmbedded.newConfiguration(),
//                dbFile);
    }

    public void close() {
//        if (db != null) {
//            db.close();
//        }
    }
}
