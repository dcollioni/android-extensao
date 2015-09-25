package com.collioni.douglas.filmes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.collioni.douglas.filmes.data.Db4oConnection;
import com.collioni.douglas.filmes.data.FilmeDao;

public class FormActivity extends ActionBarActivity {

    Db4oConnection db4o;
    FilmeDao filmeDao;

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
    }

    private void configurarBtSalvar() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
