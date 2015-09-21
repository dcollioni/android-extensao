package com.collioni.douglas.pedidos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private int quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantidade = 1;
    }

    // Método que trata o evento de click no botão menos
    public void menos(View v) {
        if (atualizarValorQuantidade()) {
            if (quantidade > 0) {
                quantidade--;
            }
            atualizarQuantidade();
        }
    }

    // Método que trata o evento de click no botão mais
    public void mais(View v) {
        if (atualizarValorQuantidade()) {
            quantidade++;
            atualizarQuantidade();
        }
    }

    public void fecharPedido(View view) {
        if (atualizarValorQuantidade()) {
            double preco = quantidade * 5.5;
            TextView tvResultado = (TextView) findViewById(R.id.tv_resultado);
            tvResultado.setText("R$ " + preco);
        }
    }
    // pega o valor do campo quantidade e atualiza a variável
    private boolean atualizarValorQuantidade() {
        EditText etQuantidade = (EditText)findViewById(R.id.et_quantidade);

        if (etQuantidade.getText().length() > 0) {
            quantidade = Integer.parseInt(etQuantidade.getText().toString());
            return true;
        }
        else {
            quantidade = 0;

            Toast.makeText(
                    getBaseContext(),
                    "Você deve informar uma quantidade",
                    Toast.LENGTH_SHORT).show();

            return false;
        }
    }

    // pega o valor da variável quantidade e atualiza o campo
    private void atualizarQuantidade() {
        EditText etQuantidade = (EditText)findViewById(R.id.et_quantidade);
        etQuantidade.setText(Integer.toString(quantidade));
    }

    // método que trata o evento de click no botão limpar
    public void limpar(View view) {
        AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.limpar)
                                .setMessage(R.string.confirma_operacao)
                                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        limparCampos();
                                    }
                                })
                                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .create();
        alert.show();
    }

    private void limparCampos() {
        EditText etQuantidade = (EditText)findViewById(R.id.et_quantidade);
        TextView tvResultado = (TextView)findViewById(R.id.tv_resultado);
        etQuantidade.setText("");
        tvResultado.setText("");
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
