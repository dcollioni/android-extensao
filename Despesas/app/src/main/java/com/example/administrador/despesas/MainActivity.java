package com.example.administrador.despesas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.security.DigestOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDespesas;
    ArrayList<Despesas> despesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDespesas = (ListView)findViewById(R.id.lv_despesas);

        configurarlvDespesas();
    }

    private void configurarlvDespesas() {

        despesas = new ArrayList<>();
        ArrayAdapter<Despesas> adapter = new ArrayAdapter<Despesas>(this, android.R.layout.simple_list_item_activated_1, despesas);

        lvDespesas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvDespesas.setAdapter(adapter);

        lvDespesas.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            ArrayList<Despesas> despesasSelecionadas = new ArrayList<>();

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                int count = lvDespesas.getCheckedItemCount();//retorna quantos itens tem marcado no menu
                mode.setTitle(Integer.toString(count));//converte o valor pra String e atualiza o valor na barra de selecao

                Despesas despesa = despesas.get(position);//pega a posicao que esta sendo selecionada
                if (checked){
                    despesasSelecionadas.add(despesa);//se estiver marcando essa despesa, adiciona no array das selecinadas
                }else{
                    despesasSelecionadas.remove(despesa);//senão remove da lista do array das selecionadas
                }

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_lista_despesas, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }


            //metodo que retorna o que foi selecionado no menu
            @Override
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.menu_excluir_despesas){

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.excluir_despesas)
                            .setMessage(R.string.cofirma_exclusao)
                            .setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Despesas despesa : despesasSelecionadas) {
                                        despesas.remove(despesa);
                                    }

                                    ((ArrayAdapter) lvDespesas.getAdapter()).notifyDataSetChanged();
                                    mode.finish();
                                                                    }
                            })
                            .create();

                }

                                        //cada item selecionado é inserido desntro da variavel despesa


               // alertDiolog.show();
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

                despesasSelecionadas.clear();//limpar para não voltar com itens carregados

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
    protected void onResume() {
        super.onResume();
        despesas.clear();

        despesas.add(new Despesas("Cartão de crédito", 100));
        despesas.add(new Despesas("Internet", 200));
        despesas.add(new Despesas("Carro", 300));
        despesas.add(new Despesas("Mercado", 400));

        ((ArrayAdapter)lvDespesas.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_novo) {
            return true;
            //TODO: abrir tela para incluir nova desésa
        }else if (id == R.id.menu_limpar){
            despesas.clear();
            ((ArrayAdapter)lvDespesas.getAdapter()).notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
