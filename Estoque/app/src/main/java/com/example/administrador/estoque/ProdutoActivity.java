package com.example.administrador.estoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

    final static String PARAM_PRODUTO = "produto";

    ListView lvProdutos;
    ArrayList <Produtos> produtos;//permite que os dados sejam manipulados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        lvProdutos = (ListView)findViewById(R.id.lv_produtos);

        configurarLvProdutos();
    }

    private void configurarLvProdutos() {
        produtos = new ArrayList<>();

        ArrayAdapter<Produtos> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);
        lvProdutos.setAdapter(adapter);

        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produtos produtoSelecionado = (Produtos)parent.getItemAtPosition(position);

                Intent intent = new Intent(ProdutoActivity.this, DetalheActivity.class);

                intent.putExtra(PARAM_PRODUTO, produtoSelecionado);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //pega a intent que gerou a abetura dessa activity
        Intent intent = getIntent();
        String categoria = intent.getStringExtra(CategoriaActivity.PARAM_CATEGORIA);

       //TODO: atualizar o titulo e carregar a lista

        setTitle(getString(R.string.title_activity_produto) + (String.format(" (%s)", categoria)));

        produtos.clear();

        if (categoria.equals(getString(R.string.eletronicos))){
            produtos.add(new Produtos(101, "Laptop", 4599.00));
            produtos.add(new Produtos(102, "Tablet", 1189.00));
            produtos.add(new Produtos(103, "Smartphone", 2199.00));

        }else if (categoria.equals(getString(R.string.escolar))){
            produtos.add(new Produtos(201, "Caderno", 20.00));
            produtos.add(new Produtos(202, "Caneta", 3.00));
            produtos.add(new Produtos(203, "Mochila", 199.00));

        }else  if (categoria.equals(getString(R.string.perfumaria))){
            produtos.add(new Produtos(301, "Perfume", 49.00));
            produtos.add(new Produtos(302, "Sabonete", 11.00));
            produtos.add(new Produtos(303, "Xampu", 21.00));
        }
        ((ArrayAdapter)lvProdutos.getAdapter()).notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_produto, menu);
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
