package com.example.elefante_afnc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticulosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticulosFragment extends Fragment {
    private List<Articulo> informacion;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ArticulosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticulosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticulosFragment newInstance(String param1, String param2) {
        ArticulosFragment fragment = new ArticulosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articulos, container, false);
        informacion = obtener_informacion();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());

        adapter = new ArticulosAdapter(informacion,
                R.layout.item_articulos,
                new ArticulosAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Articulo articulo, int position) {

                    }
                });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<Articulo> obtener_informacion(){
        ArrayList<Articulo> lista_articulos = new ArrayList<Articulo>();

        lista_articulos.add(new Articulo("Animales africanos que puedes tener de mascota,",
                "Mascotas exoticas",
                R.drawable.mascotas_africa,
                "Los animales que puedes conseguir de manera totalmente legal..."));

        lista_articulos.add(new Articulo("Animales más peligrosos de africa,",
                "No querras encontrartelos de frente",
                R.drawable.peligrosos,
                "Los animales mas peligrosos que puedes encontrar en Africa"));

        return lista_articulos;
    }
}