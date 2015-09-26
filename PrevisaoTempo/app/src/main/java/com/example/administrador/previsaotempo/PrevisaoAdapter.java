package com.example.administrador.previsaotempo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrador on 26/09/2015.
 */
public class PrevisaoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Previsao> previsoes;

    public PrevisaoAdapter(Context context, ArrayList<Previsao> previsoes) {
        super();
        this.context = context;
        this.previsoes = previsoes;
    }

    @Override
    public int getCount() {
        return previsoes.size();
    }

    @Override
    public Object getItem(int position) {
        return previsoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.previsao_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Previsao previsao = (Previsao)getItem(position);

        viewHolder.tvData.setText(previsao.getData());
        viewHolder.tvTempMax.setText(previsao.getTempMax() + "°");
        viewHolder.tvTempMin.setText(previsao.getTempMin() + "°");

        if (previsao.getDescricao().toLowerCase().contains("chuva")) {
            previsao.setImagemId(R.drawable.chuva);
        }
        else if (previsao.getDescricao().toLowerCase().contains("ensolarado")) {
            previsao.setImagemId(R.drawable.ensolarado);
        }
        else if (previsao.getDescricao().toLowerCase().contains("nublado")) {
            previsao.setImagemId(R.drawable.nublado);
        }
        else if (previsao.getDescricao().toLowerCase().contains("parcialmente nublado")) {
            previsao.setImagemId(R.drawable.parcialmente_nublado);
        }
        else if (previsao.getDescricao().toLowerCase().contains("trovoadas")) {
            previsao.setImagemId(R.drawable.trovoadas);
        }
        else if (previsao.getDescricao().toLowerCase().contains("tempo bom")) {
            previsao.setImagemId(R.drawable.tempo_bom);
        }

        viewHolder.ivImagem.setImageResource(previsao.getImagemId());

        return convertView;
    }

    public static class ViewHolder {
        public final ImageView ivImagem;
        public final TextView tvData;
        public final TextView tvTempMax;
        public final TextView tvTempMin;

        public ViewHolder(View view) {
            ivImagem = (ImageView)view.findViewById(R.id.iv_imagem);
            tvData = (TextView)view.findViewById(R.id.tv_data);
            tvTempMax = (TextView)view.findViewById(R.id.tv_temp_max);
            tvTempMin = (TextView)view.findViewById(R.id.tv_temp_min);
        }
    }
}
