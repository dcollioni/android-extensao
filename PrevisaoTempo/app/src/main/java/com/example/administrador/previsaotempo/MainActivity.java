package com.example.administrador.previsaotempo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String URL_CONSULTA = "http://developers.agenciaideias.com.br/tempo/json/";

    final String P_PREVISOES = "previsoes";
    final String P_DATA = "data";
    final String P_DESCRICAO = "descricao";
    final String P_IMAGEM = "imagem";
    final String P_TEMP_MAX = "temperatura_max";
    final String P_TEMP_MIN = "temperatura_min";

    final static String PARAM_PREVISAO = "previsao";

    EditText etEstado, etCidade;
    Button btConsultar;
    ListView lvPrevisoes;
    ProgressBar pbConsultando;

    ArrayList<Previsao> previsoes;
    PrevisaoAdapter adapterPrevisoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEstado = (EditText)findViewById(R.id.et_estado);
        etCidade = (EditText)findViewById(R.id.et_cidade);
        btConsultar = (Button)findViewById(R.id.bt_consultar);
        lvPrevisoes = (ListView)findViewById(R.id.lv_previsoes);
        pbConsultando = (ProgressBar)findViewById(R.id.pb_consultando);

        configurarLvPrevisoes();
        configurarBtConsultar();
    }

    private void configurarBtConsultar() {
        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String estado = etEstado.getText().toString();
                String cidade = etCidade.getText().toString();
                String url = URL_CONSULTA + cidade + "-" + estado;

                new ConsultaTask().execute(url);
            }
        });
    }

    private void configurarLvPrevisoes() {
        previsoes = new ArrayList<>();

        adapterPrevisoes = new PrevisaoAdapter(this, previsoes);

        lvPrevisoes.setAdapter(adapterPrevisoes);

        lvPrevisoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Previsao previsao = (Previsao)parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
                intent.putExtra(PARAM_PREVISAO, previsao);
                startActivity(intent);
            }
        });
    }

    private class ConsultaTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            publishProgress();

            String urlString = params[0];

            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

                int code = urlConnection.getResponseCode();
                Log.i("code", "Response: " + code);

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                StringBuffer buffer = new StringBuffer("");
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                reader.close();
                in.close();

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            previsoes.clear();
            adapterPrevisoes.notifyDataSetChanged();
            pbConsultando.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pbConsultando.setVisibility(View.GONE);

            if (s == null || s.isEmpty()) {
                Toast.makeText(getBaseContext(), R.string.nenhum_resultado, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject json = new JSONObject(s);
                JSONArray jsonPrevisoes = json.getJSONArray(P_PREVISOES);

                for (int i = 0; i < jsonPrevisoes.length(); i++) {
                    JSONObject jsonPrevisao = jsonPrevisoes.getJSONObject(i);

                    String data = jsonPrevisao.getString(P_DATA);
                    String descricao = jsonPrevisao.getString(P_DESCRICAO);
                    String imagem = jsonPrevisao.getString(P_IMAGEM);
                    int tempMax = jsonPrevisao.getInt(P_TEMP_MAX);
                    int tempMin = jsonPrevisao.getInt(P_TEMP_MIN);

                    Previsao previsao = new Previsao(data, descricao, imagem, tempMax, tempMin);
                    previsoes.add(previsao);
                }

                adapterPrevisoes.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
        if (id == R.id.menu_limpar) {
            etEstado.setText(null);
            etCidade.setText(null);
            previsoes.clear();
            adapterPrevisoes.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
