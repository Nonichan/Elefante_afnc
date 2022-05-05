package com.example.elefante_afnc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class misAnimalesAdapter extends BaseAdapter {
    Context context;
    List<ModelAnimal> lista_mis_animales;
    private int layout;

    public misAnimalesAdapter(Context context,
                             List<ModelAnimal> lista_mis_animales,
                             int layout){
        this.context = context;
        this.lista_mis_animales = lista_mis_animales;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lista_mis_animales.size();
    }

    @Override
    public Object getItem(int i) {
        return lista_mis_animales.get(i);
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
            vh.tv_my_nombre_animal = view.findViewById(R.id.tv_my_nombre_animal);
            vh.tv_my_color_animal = view.findViewById(R.id.tv_my_color_animal);
            view.setTag(vh);
        }else {
            vh = (MyViewHolder) view.getTag();
        }

        ModelAnimal animal = lista_mis_animales.get(i);
        vh.tv_my_nombre_animal.setText((animal.getNombre()));
        vh.tv_my_color_animal.setText((animal.getColor()));
        return view;
    }

    public class MyViewHolder{
        TextView tv_my_nombre_animal;
        TextView tv_my_color_animal;
    }
}
