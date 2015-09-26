package com.example.administrador.consultacep;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    final String URL_CONSULTA = "http://correiosapi.apphb.com/cep/";

    final String P_LOGRADOURO = "logradouro";
    final String P_TIPO_LOGRADOURO = "tipoDeLogradouro";
    final String P_BAIRRO = "bairro";
    final String P_CIDADE = "cidade";
    final String P_ESTADO = "estado";

    EditText etCep;
    Button btBuscar;
    TextView tvLogradouro, tvBairro, tvCidade, tvEstado;
    ProgressBar pbConsultando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCep = (EditText)findViewById(R.id.et_cep);
        btBuscar = (Button)findViewById(R.id.bt_buscar);
        tvLogradouro = (TextView)findViewById(R.id.tv_logradouro);
        tvBairro = (TextView)findViewById(R.id.tv_bairro);
        tvCidade = (TextView)findViewById(R.id.tv_cidade);
        tvEstado = (TextView)findViewById(R.id.tv_estado);
        pbConsultando = (ProgressBar)findViewById(R.id.pb_consultando);

        configurarBtBuscar();
    }

    private void configurarBtBuscar() {
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = etCep.getText().toString();
                String url = URL_CONSULTA + cep;

                new ConsultaTask().execute(url);
            }
        });
    }

    private class ConsultaTask extends AsyncTask<String, Void, String>{

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

            pbConsultando.setVisibility(View.VISIBLE);
            tvLogradouro.setText(null);
            tvBairro.setText(null);
            tvCidade.setText(null);
            tvEstado.setText(null);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pbConsultando.setVisibility(View.GONE);

            try {
                if (s == null || s.isEmpty()) {
                    Toast.makeText(
                            getBaseContext(), R.string.cep_nao_encontrado, Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                JSONObject json = new JSONObject(s);

                String logradouro = json.getString(P_LOGRADOURO);
                String tipoLogradouro = json.getString(P_TIPO_LOGRADOURO);
                String bairro = json.getString(P_BAIRRO);
                String cidade = json.getString(P_CIDADE);
                String estado = json.getString(P_ESTADO);

                tvLogradouro.setText(tipoLogradouro + " " + logradouro);
                tvBairro.setText(getString(R.string.bairro) + bairro);
                tvCidade.setText(getString(R.string.cidade) + cidade);
                tvEstado.setText(getString(R.string.estado) + estado);

            } catch (JSONException e) {
                Toast.makeText(
                        getBaseContext(), "Erro no JSON: " + e.getMessage(), Toast.LENGTH_LONG
                ).show();
            } catch (Exception e) {
                Toast.makeText(
                        getBaseContext(), "Erro no JSON: " + e.getMessage(), Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    private void request(String url) {

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
            etCep.setText(null);
            tvLogradouro.setText(null);
            tvBairro.setText(null);
            tvCidade.setText(null);
            tvEstado.setText(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
