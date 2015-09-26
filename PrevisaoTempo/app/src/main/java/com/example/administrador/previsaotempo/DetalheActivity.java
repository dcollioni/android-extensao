package com.example.administrador.previsaotempo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    TextView tvData, tvDescricao, tvTempMax, tvTempMin;
    ImageView ivImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        tvData = (TextView)findViewById(R.id.tv_detalhe_data);
        tvDescricao = (TextView)findViewById(R.id.tv_detalhe_descricao);
        tvTempMax = (TextView)findViewById(R.id.tv_detalhe_temp_max);
        tvTempMin = (TextView)findViewById(R.id.tv_detalhe_temp_min);
        ivImagem = (ImageView)findViewById(R.id.iv_detalhe_imagem);

        Previsao previsao = (Previsao)getIntent()
                    .getSerializableExtra(MainActivity.PARAM_PREVISAO);

        tvData.setText(previsao.getData());
        tvDescricao.setText(previsao.getDescricao());
        tvTempMax.setText(previsao.getTempMax() + "°");
        tvTempMin.setText(previsao.getTempMin() + "°");
        ivImagem.setImageResource(previsao.getImagemId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
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
