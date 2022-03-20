package com.example.elefante_afnc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Nav_lista_animales_main_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Nav_lista_animales_main_Fragment extends Fragment {

    //codigo pegado de MyApp_afnc
    private ArrayList<String> nombres_animales;

    private Integer[] id_imagenes_animanes = {
            R.drawable.leon,
            R.drawable.cebra,
            R.drawable.hipopotamo,
            R.drawable.hiena,
            R.drawable.caiman,
            R.drawable.suricata,
            R.drawable.jirafa,
            R.drawable.elefante64,
            R.drawable.rinoceronte
    };

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Nav_lista_animales_main_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Nav_lista_animales_main_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Nav_lista_animales_main_Fragment newInstance(String param1, String param2) {
        Nav_lista_animales_main_Fragment fragment = new Nav_lista_animales_main_Fragment();
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

        ListView list_view_animales;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav_lista_animales_main_, container, false);

        //codigo pegado de MyApp_afnc
        list_view_animales = view.findViewById(R.id.listviewAnimales);

        nombres_animales = new ArrayList<String>();
        nombres_animales.add("Leon");
        nombres_animales.add("Cebra");
        nombres_animales.add("Hipopotamo");
        nombres_animales.add("Hiena");
        nombres_animales.add("Caiman");
        nombres_animales.add("Suricata");
        nombres_animales.add("Jirafa");
        nombres_animales.add("Elefante");
        nombres_animales.add("Rinoceronte");

        List_animal_adapter adapter;
        adapter = new List_animal_adapter(
                getActivity(), R.layout.item_list_animales, nombres_animales,
                id_imagenes_animanes
        );
        list_view_animales.setAdapter(adapter);

        return view;
    }
}