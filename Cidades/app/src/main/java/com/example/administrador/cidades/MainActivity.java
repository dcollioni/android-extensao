package com.example.administrador.cidades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spEstados, spCidades;
    TextView tvPopulacao;
    ImageView ivFoto;
    ArrayList<Cidade> cidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spEstados = (Spinner)findViewById(R.id.sp_estados);
        spCidades = (Spinner)findViewById(R.id.sp_cidades);
        tvPopulacao = (TextView)findViewById(R.id.tv_populacao);
        ivFoto = (ImageView)findViewById(R.id.iv_foto);

        configurarSpEstados();
        configurarSpCidades();
    }

    private void configurarSpCidades() {
        cidades = new ArrayList<>();

        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, cidades);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCidades.setAdapter(adapter);

        spCidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cidade cidadeSelecionada = (Cidade)parent.getItemAtPosition(position);

                tvPopulacao.setText("População: " + cidadeSelecionada.getPopulacao());
                ivFoto.setImageResource(cidadeSelecionada.getFoto());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void configurarSpEstados() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.estados, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spEstados.setAdapter(adapter);

        spEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String estadoSelecionado = (String)parent.getItemAtPosition(position);

                cidades.clear();

                if (estadoSelecionado.equals(getString(R.string.pr))) {
                    cidades.add(new Cidade("Curitiba", 800000, R.drawable.cidade1));
                    cidades.add(new Cidade("Londrina", 650000, R.drawable.cidade2));

                } else if (estadoSelecionado.equals(getString(R.string.rs))) {
                    cidades.add(new Cidade("Porto Alegre", 2000000, R.drawable.cidade1));
                    cidades.add(new Cidade("São Leopoldo", 500000, R.drawable.cidade2));

                } else if (estadoSelecionado.equals(getString(R.string.sc))) {
                    cidades.add(new Cidade("Florianópolis", 1500000, R.drawable.cidade1));
                    cidades.add(new Cidade("Joinvile", 485000, R.drawable.cidade2));

                }

                ArrayAdapter adapter = (ArrayAdapter)spCidades.getAdapter();
                adapter.notifyDataSetChanged();
                spCidades.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
