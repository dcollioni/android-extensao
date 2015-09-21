package com.example.administrador.estoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    TextView tvNome, tvCodigo, tvPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        tvCodigo = (TextView)findViewById(R.id.tv_codigo);
        tvNome = (TextView)findViewById(R.id.tv_nome);
        tvPreco = (TextView)findViewById(R.id.tv_preco);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        Produtos produto = (Produtos)intent.getSerializableExtra(ProdutoActivity.PARAM_PRODUTO);
        tvCodigo.setText(String.format("Código: %s", produto.getCodigo()));
        tvNome.setText(String.format("Nome: %s", produto.getNome()));
        tvPreco.setText(String.format("Preço: %s", produto.getPreco()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
