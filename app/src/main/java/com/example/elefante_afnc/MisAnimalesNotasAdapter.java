package com.example.elefante_afnc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MisAnimalesNotasAdapter extends BaseAdapter {
    Context context;
    List<ModelAnimalNotas> lista_notas;
    private int layout;

    public MisAnimalesNotasAdapter(Context context,
                                   List<ModelAnimalNotas> lista_notas,
                                   int layout){
        this.context = context;
        this.lista_notas = lista_notas;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lista_notas.size();
    }

    @Override
    public Object getItem(int i) {
        return lista_notas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder vh;
        if(view==null){
            view = LayoutInflater.from(context).inflate(layout, null);
            vh = new MyViewHolder();
            vh.tv_fecha_nota = view.findViewById(R.id.tv_fecha_nota);
            vh.tv_nota = view.findViewById(R.id.tv_nota);
            view.setTag(vh);
        }else {
            vh = (MyViewHolder) view.getTag();
        }

        ModelAnimalNotas notas = lista_notas.get(i);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String sfecha = df.format(notas.getFecha());
        vh.tv_fecha_nota.setText(sfecha);
        vh.tv_nota.setText((notas.getNota()));
        return view;
    }

    public class MyViewHolder{
        TextView tv_fecha_nota;
        TextView tv_nota;
    }
}
