package com.example.administrador.cadastrofilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrador.cadastrofilmes.data.Db4oConnection;
import com.example.administrador.cadastrofilmes.data.FilmeDao;

public class FormActivity extends AppCompatActivity {

    Db4oConnection db4o;
    FilmeDao filmeDao;

    private long filmeId;

    EditText etTitulo, etAno, etDuracao;
    Spinner spCategoria;
    Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitulo = (EditText)findViewById(R.id.et_titulo);
        etAno = (EditText)findViewById(R.id.et_ano);
        etDuracao = (EditText)findViewById(R.id.et_duracao);
        spCategoria = (Spinner)findViewById(R.id.sp_categoria);
        btSalvar = (Button)findViewById(R.id.bt_salvar);

        configurarBtSalvar();
        configurarDb4o();

        Intent i = getIntent();
        filmeId = i.getLongExtra(MainActivity.PARAM_FILME_ID, 0);
    }

    private void configurarDb4o() {
        String dir = getDir("data", 0) + "/";
        db4o = new Db4oConnection(dir);
        filmeDao = new FilmeDao(db4o);
    }

    private void configurarBtSalvar() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTitulo.getText().toString();
                String ano = etAno.getText().toString();
                String duracao = etDuracao.getText().toString();
                String categoria = spCategoria.getSelectedItem().toString();

                if (titulo.isEmpty() || ano.isEmpty() || duracao.isEmpty()) {
                    Toast.makeText(
                            getBaseContext(), R.string.preencha_dados_filme, Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                Filme filme = new Filme();
                filme.setTitulo(titulo);
                filme.setAno(Integer.parseInt(ano));
                filme.setDuracao(Integer.parseInt(duracao));
                filme.setCategoria(categoria);

                if (filmeId == 0) {
                    filmeDao.inserir(filme);
                }
                else {
                    filmeDao.atualizar(filme, filmeId);
                }

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.open();

        // atualização
        if (filmeId > 0) {
            setTitle(getString(R.string.editar_filme));

            Filme filme = filmeDao.buscar(filmeId);

            etTitulo.setText(filme.getTitulo());
            etAno.setText(Integer.toString(filme.getAno()));
            etDuracao.setText(Integer.toString(filme.getDuracao()));

            int position = ((ArrayAdapter<String>)spCategoria.getAdapter())
                                .getPosition(filme.getCategoria());
            spCategoria.setSelection(position);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_limpar) {
            etTitulo.setText(null);
            etAno.setText(null);
            etDuracao.setText(null);
            spCategoria.setSelection(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
