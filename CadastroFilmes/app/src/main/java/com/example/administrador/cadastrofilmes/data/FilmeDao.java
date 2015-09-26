package com.example.administrador.cadastrofilmes.data;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.example.administrador.cadastrofilmes.Filme;

import java.util.ArrayList;

/**
 * Created by Administrador on 25/09/2015.
 */
public class FilmeDao {
    private Db4oConnection db4o;

    public FilmeDao(Db4oConnection db4o) {
        this.db4o = db4o;
    }

    public void inserir(Filme filme) {
        db4o.db().store(filme);
    }

    public ArrayList<Filme> listar() {
        ObjectSet<Filme> filmes = db4o.db().query(Filme.class);
        return new ArrayList<>(filmes);
    }

    public long getId(Filme filme) {
        return db4o.db().ext().getID(filme);
    }

    public Filme buscar(long id) {
        Filme filme = db4o.db().ext().getByID(id);
        db4o.db().ext().activate(filme); // carrega do banco as propriedades do objeto
        return filme;
    }

    public void atualizar(Filme filme, long id) {
        Filme filmeCadastrado = buscar(id);

        filmeCadastrado.setTitulo(filme.getTitulo());
        filmeCadastrado.setAno(filme.getAno());
        filmeCadastrado.setDuracao(filme.getDuracao());
        filmeCadastrado.setCategoria(filme.getCategoria());

        db4o.db().store(filmeCadastrado);
    }

    public void excluir(Filme filme) {
        db4o.db().delete(filme);
    }

    public ArrayList<Filme> pesquisar(final String filtro) {
        ObjectSet<Filme> filmes = db4o.db().query(new Predicate<Filme>() {
            @Override
            public boolean match(Filme filme) {
                return filme.getTitulo().toLowerCase()
                            .contains(filtro.toLowerCase());
            }
        });
        return new ArrayList<>(filmes);
    }
}
