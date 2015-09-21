package com.example.administrador.estoque;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etSenha;
    Button btEntrar;

    final String USUARIO_CORRETO = "admin";//declarando váriaveis estaticas
    final String SENHA_CORRETA = "abc123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = (EditText)findViewById(R.id.et_usuario);
        etSenha = (EditText)findViewById(R.id.et_senha);
        btEntrar = (Button)findViewById(R.id.bt_entrar);

        configurarBotaoEntrar();

    }

    private void configurarBotaoEntrar() {

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString();
                String senha = etSenha.getText().toString();

                if (usuario.equals(USUARIO_CORRETO) && senha.equals(SENHA_CORRETA)){
                    //Origem e destino
                    Intent intent = new Intent(MainActivity.this, CategoriaActivity.class);//propriedade Intent chama uma nova janela, precisa colocar a origem e o destino
                    startActivity(intent);

                }else {

                    Toast.makeText(getBaseContext(), R.string.usuario_senha_invalidos, Toast.LENGTH_SHORT).show();
                }
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
