package com.example.elefante_afnc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class List_animal_adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> nombres_animales;
    private Integer[] id_imagenes_animales;

    public List_animal_adapter(Context context, int layout, ArrayList<String> nombres_animales, Integer[] id_imagenes_animales) {
        this.context = context;
        this.layout = layout;
        this.nombres_animales = nombres_animales;
        this.id_imagenes_animales = id_imagenes_animales;
    }

    @Override
    public int getCount() {
        return this.nombres_animales.size();
    }

    @Override
    public Object getItem(int i) {
        return this.nombres_animales.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View v = view;
        if (v == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            v = layoutInflater.inflate(R.layout.item_list_animales, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = v.findViewById(R.id.iv_animal);
            viewHolder.textView = v.findViewById(R.id.tvnombre_animal);
            v.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.textView.setText((nombres_animales.get(i)));
        viewHolder.imageView.setImageResource(id_imagenes_animales[i]);

        return v;
    }

    static class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
