package com.example.administrador.tiposlistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    ListView lvFrutas;
    Spinner spTiposListas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFrutas = (ListView)findViewById(R.id.lv_frutas);
        spTiposListas = (Spinner)findViewById(R.id.sp_tipo_lista);

        /*configurarLvFrutas();
        configurarlvFrutasSingleRadio();//exemplo com lista single, como foi referenciado depois sobrescreve o anterior
        configurarLvFrutasSingleActivated();
        configurarLvFrutasMultipleActivated();
        configurarLvFrutasMultipleCheckBox(); //MULTIPLA SELECAO COM CHECKBOX
        configurarLvFrutasMultipleChecked();*/
        configurarSpTiposListas();//criado para substituir os exemplos de cima
    }

    private void configurarSpTiposListas() {

        spTiposListas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tipoSelecionado = (String)parent.getItemAtPosition(position);

                if (tipoSelecionado.equals(getString(R.string.none_simple))){
                    configurarLvFrutas();

                }else if (tipoSelecionado.equals(getString(R.string.single_radio))){
                    configurarlvFrutasSingleRadio();

                }else if (tipoSelecionado.equals(getString(R.string.single_activated))){
                    configurarLvFrutasSingleActivated();

                }else if (tipoSelecionado.equals(getString(R.string.multiple_activated))){
                    configurarLvFrutasMultipleActivated();

                }else if (tipoSelecionado.equals(getString(R.string.multiple_checkbox))){
                    configurarLvFrutasMultipleCheckBox();

                }else if (tipoSelecionado.equals(getString(R.string.multiple_checked))){
                    configurarLvFrutasMultipleChecked();
                }
            }
        });
    }

    private void configurarLvFrutasMultipleChecked() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frutas, android.R.layout.simple_list_item_checked);
        lvFrutas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvFrutas.setAdapter(adapter);
    }

    private void configurarLvFrutasMultipleCheckBox() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frutas, android.R.layout.simple_list_item_multiple_choice);
        lvFrutas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvFrutas.setAdapter(adapter);
    }

    private void configurarLvFrutasMultipleActivated() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frutas, android.R.layout.simple_list_item_activated_1);
        lvFrutas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvFrutas.setAdapter(adapter);
    }

    private void configurarLvFrutasSingleActivated() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frutas, android.R.layout.simple_list_item_activated_1);
        lvFrutas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//aqui altera o tipo de selecao da lista
        lvFrutas.setAdapter(adapter);
    }

    private void configurarlvFrutasSingleRadio() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frutas, android.R.layout.simple_list_item_single_choice);
        lvFrutas.setAdapter(adapter);

    }

    private void configurarLvFrutas() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frutas, android.R.layout.simple_list_item_1);//chama a lista de array
        lvFrutas.setAdapter(adapter);//seta os valoes do array
        lvFrutas.setChoiceMode(ListView.CHOICE_MODE_NONE);//tipo de selecao usado na lista, permite selecionar apenas um elemento
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
