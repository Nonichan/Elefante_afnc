package com.example.elefante_afnc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder> {
    private List<Articulo> articulos;
    private int layout;
    private Context context;
    private OnItemClickListener itemClickListener;

    public ArticulosAdapter(List<Articulo> articulos, int layout, OnItemClickListener itemClickListener) {
        this.articulos = articulos;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                layout, parent, false
        );
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(articulos.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //elementos de item_articulos
        public Toolbar toolbarCard;
        public ImageView imageCard;
        public LinearLayout mas_info;
        public TextView tv_content;
        public ViewGroup detalles;
        public ImageView image_more;

        public ViewHolder(View itemView){
            super(itemView);
            toolbarCard = itemView.findViewById(R.id.toolbarCard);
            imageCard = itemView.findViewById(R.id.imageCard);
            mas_info = itemView.findViewById(R.id.mas_info);
            tv_content = itemView.findViewById(R.id.tv_content);
            detalles = itemView.findViewById(R.id.detalles);
            image_more = itemView.findViewById(R.id.image_more);

        }

        public void bind(final Articulo articulo, OnItemClickListener listener){
            //sustituir cada elemento por su valor

            toolbarCard.setTitle(articulo.getTitle());
            toolbarCard.setSubtitle(articulo.getSubtitle());
            toolbarCard.inflateMenu(R.menu.card_menu);

            toolbarCard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.card_compartir:
                            Toast.makeText(context, "Compartiendo articulo",
                                    Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            });

            imageCard.setImageResource(articulo.getImage());

            tv_content.setText(articulo.getContent());
            mas_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (detalles.getVisibility()==View.GONE){
                        ExpandCollapse.expand(detalles, 250);
                        image_more.setImageResource(R.drawable.more);
                        //rotamos el icono 180grados
                        Animation animation = new RotateAnimation(
                                0.0f, -180.0f,
                                Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF,
                                0.5f
                        );
                        animation.setFillAfter(true);
                        animation.setDuration(250);
                        image_more.startAnimation(animation);


                    }
                    else{
                        ExpandCollapse.collapse(detalles, 250);
                        image_more.setImageResource(R.drawable.less);
                        //rotamos el icono 180grados
                        Animation animation = new RotateAnimation(
                                0.0f, 180.0f,
                                Animation.RELATIVE_TO_SELF,
                                0.5f, Animation.RELATIVE_TO_SELF,
                                0.5f
                        );
                        animation.setFillAfter(true);
                        animation.setDuration(250);
                        image_more.startAnimation(animation);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Articulo articulo, int position);
    }
}
