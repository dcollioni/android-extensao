package com.collioni.douglas.filmes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.collioni.douglas.filmes.data.Db4oConnection;
import com.collioni.douglas.filmes.data.FilmeDao;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    Db4oConnection db4o;
    FilmeDao filmeDao;

    ListView lvFilmes;
    ArrayAdapter<Filme> adapterFilmes;
    ArrayList<Filme> filmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFilmes = (ListView)findViewById(R.id.lv_filmes);

        configurarLvFilmes();
    }

    private void configurarLvFilmes() {
        filmes = new ArrayList<>();

        adapterFilmes = new ArrayAdapter<Filme>(
                this, android.R.layout.simple_list_item_1, filmes
        );

        lvFilmes.setAdapter(adapterFilmes);
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

        return super.onOptionsItemSelected(item);
    }
}
