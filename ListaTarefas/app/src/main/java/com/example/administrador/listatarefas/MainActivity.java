package com.example.administrador.listatarefas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTarefas;
    EditText etTarefa;
    Button btIncluir;
    ArrayList<CharSequence> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTarefas = (ListView)findViewById(R.id.lv_tarefas);
        etTarefa = (EditText)findViewById(R.id.et_tarefa);
        btIncluir = (Button)findViewById(R.id.bt_incluir);

        configurarBtIncluir();
        configurarLvTarefas();

        registerForContextMenu(lvTarefas);//fica registrado que a lista de tarefas vai possuir um menu de contexto
        Log.i("Ciclo de Vida", "OnCreate");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo de Vida", "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de Vida", "OnPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo de Vida", "OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de Vida", "OnDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de Vida", "OnStop");
    }

    private void configurarLvTarefas() {

        tarefas = new ArrayList<>();//consegue adicionar ou remover item
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, tarefas);
        lvTarefas.setAdapter(adapter);

        lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence tarefa = (CharSequence)parent.getItemAtPosition(position);

                Toast.makeText(getBaseContext(), tarefa, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarBtIncluir() {

        btIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarefa = etTarefa.getText().toString();

                if (tarefa.isEmpty()){
                    Toast.makeText(getBaseContext(), R.string.informe_descricao, Toast.LENGTH_SHORT).show();
                    return;
                }

                tarefas.add(tarefa);//Array de dados

                //notifica o adapter de que os dados foram alterados
                ((ArrayAdapter)lvTarefas.getAdapter()).notifyDataSetChanged();

                etTarefa.setText(null); //limpa o EditText depois de incluir a tarefa
            }
        });
    }

    //Cria o menu de contexto no compondente desejado (no caso no listview tarefas
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lv_tarefas){
            getMenuInflater().inflate(R.menu.menu_lista_tarefas, menu); //Busca o arquivo do xml no menu lista
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_excluir_tarefa){
            final int position = ((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position;//o menu item possui um id e um meotodo que possui informacoes sobre o menu. Uma das propriedades é a posicion

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.excluir_tarefa)
                    .setMessage(R.string.confirma_exclusao_tarefa)
                    .setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tarefas.remove(position);
                            ((ArrayAdapter)lvTarefas.getAdapter()).notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })


                    .create();

                    alertDialog.show();

            //tarefas.remove(position);//remove o intem do indice que é a position
            //((ArrayAdapter)lvTarefas.getAdapter()).notifyDataSetChanged();//informa ao adapter que os dados mudaram

        }

        return super.onContextItemSelected(item);



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
        if (id == R.id.menu_limpar_lista) {

            tarefas.clear();//remove a lista de tarefas
            ((ArrayAdapter)lvTarefas.getAdapter()).notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
