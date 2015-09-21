package com.collioni.douglas.calculadoraluz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends ActionBarActivity {

    EditText etKwh, etTaxaIcms;
    RadioGroup rgTipoConta;
    TextView tvTotal;
    CheckBox cbIncideIcms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etKwh = (EditText)findViewById(R.id.et_kwh);
        rgTipoConta = (RadioGroup)findViewById(R.id.rg_tipo_conta);
        tvTotal = (TextView)findViewById(R.id.tv_total);
        cbIncideIcms = (CheckBox)findViewById(R.id.cb_incide_icms);
        etTaxaIcms = (EditText)findViewById(R.id.et_taxa_icms);

        cbIncideIcms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etTaxaIcms.setEnabled(isChecked);
                etTaxaIcms.setText("");
            }
        });
    }

    public void calcular(View view) {
        if (etKwh.getText().length() <= 0) {

            Toast.makeText(
                    getBaseContext(),
                    "Kwh deve ser preenchido",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        double kwh = Double.parseDouble(etKwh.getText().toString());

        double tarifa = buscarTarifa();

        double total = kwh * tarifa;

        if (etTaxaIcms.getText().length() > 0) {
            double taxaIcms = Double.parseDouble(etTaxaIcms.getText().toString());
            total += total * (taxaIcms / 100);
        }

        tvTotal.setText("Total: R$ " + formatarMoeda(total));
    }

    private String formatarMoeda(double valor) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(valor);
    }

    private double buscarTarifa() {
        int contaSelecionada = rgTipoConta.getCheckedRadioButtonId();

        switch (contaSelecionada) {
            case R.id.rb_residencial:
                return 0.25;
            case R.id.rb_comercial:
                return 0.32;
            case R.id.rb_rural:
                return 0.18;
            default:
                return 0;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
