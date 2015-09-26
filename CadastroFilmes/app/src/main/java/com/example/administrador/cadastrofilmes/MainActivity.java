package com.example.administrador.cadastrofilmes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrador.cadastrofilmes.data.Db4oConnection;
import com.example.administrador.cadastrofilmes.data.FilmeDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String PARAM_FILME_ID = "filmeId";

    Db4oConnection db4o;
    FilmeDao filmeDao;

    ListView lvFilmes;
    ArrayAdapter<Filme> adapterFilmes;
    ArrayList<Filme> filmes;

    EditText etPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFilmes = (ListView)findViewById(R.id.lv_filmes);
        etPesquisar = (EditText)findViewById(R.id.et_pesquisar);

        configurarLvFilmes();
        configurarEtPesquisar();
        configurarDb4o();
    }

    private void configurarEtPesquisar() {
        etPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String filtro = etPesquisar.getText().toString();
                
                filmes.clear();
                filmes.addAll(filmeDao.pesquisar(filtro));
                adapterFilmes.notifyDataSetChanged();
            }
        });
    }

    private void configurarDb4o() {
        String dir = getDir("data", 0) + "/";
        db4o = new Db4oConnection(dir);
        filmeDao = new FilmeDao(db4o);
    }

    private void configurarLvFilmes() {
        filmes = new ArrayList<>();

        adapterFilmes = new ArrayAdapter<Filme>(
                this, android.R.layout.simple_list_item_activated_1, filmes
        );

        lvFilmes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvFilmes.setAdapter(adapterFilmes);

        lvFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) parent.getItemAtPosition(position);
                long filmeId = filmeDao.getId(filme);

                Intent i = new Intent(MainActivity.this, FormActivity.class);
                i.putExtra(PARAM_FILME_ID, filmeId);
                startActivity(i);
            }
        });

        lvFilmes.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            ArrayList<Filme> filmesSelecionados = new ArrayList<>();

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                int count = lvFilmes.getCheckedItemCount();
                mode.setTitle(Integer.toString(count));

                Filme filme = filmes.get(position);

                if (checked) {
                    filmesSelecionados.add(filme);
                }
                else {
                    filmesSelecionados.remove(filme);
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_filmes, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_excluir_filmes) {

                    AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.excluir_filmes)
                            .setMessage(R.string.confirma_exclusao)
                            .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Filme filme : filmesSelecionados) {
                                        filmeDao.excluir(filme);
                                    }
                                    mode.finish();
                                    carregarFilmes();
                                }
                            })
                            .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create();

                    alert.show();
                    return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                filmesSelecionados.clear();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.open();

        carregarFilmes();
    }

    private void carregarFilmes() {
        filmes.clear();
        filmes.addAll(filmeDao.listar());
        adapterFilmes.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_novo_filme) {
            Intent i = new Intent(MainActivity.this, FormActivity.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.menu_excluir_todos_filmes) {
            AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.excluir_filmes)
                    .setMessage(R.string.confirma_exclusao)
                    .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (Filme filme : filmes) {
                                filmeDao.excluir(filme);
                            }
                            carregarFilmes();
                        }
                    })
                    .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create();

            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
