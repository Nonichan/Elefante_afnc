package com.example.elefante_afnc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisAnimalesNotasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisAnimalesNotasFragment extends Fragment {
    private Realm realm;
    private FloatingActionButton fab;
    private GridView gridView;
    private RealmList<ModelAnimalNotas> notas;
    private int animalId;
    private ModelAnimal animal;
    private MisAnimalesNotasAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MisAnimalesNotasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisAnimalesNotasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisAnimalesNotasFragment newInstance(String param1, String param2) {
        MisAnimalesNotasFragment fragment = new MisAnimalesNotasFragment();
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
        View view = inflater.inflate(R.layout.fragment_mis_animales_notas, container, false);

        gridView = view.findViewById(R.id.gridViewn_Notas);
        fab = view.findViewById(R.id.fab_notas);
        realm = Realm.getDefaultInstance();

        Bundle datos = getArguments();
        if (datos != null){
            animalId = datos.getInt("animalId");
        }
        animal = realm.where(ModelAnimal.class).equalTo("id", animalId)
                .findFirst();
        notas = animal.getNotas();

        //animales = realm.where(ModelAnimal.class).findAll();
        //animales.addChangeListener(this);

        adapter = new MisAnimalesNotasAdapter(getActivity(),
                notas, R.layout.item_grid_mis_animales_notas);
        gridView.setAdapter(adapter);
        //gridView.setOnItemClickListener(this);
        registerForContextMenu(gridView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertAdd("Agrega una nota para" +
                        animal.getNombre()
                        , "Escribe una nota para este animal");
            }
        });
        return view;
    }

    public void showAlertAdd(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);
        View vista_dialog = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_animales_notas, null);
        builder.setView(vista_dialog);
        EditText edit_fecha_nota = vista_dialog.findViewById(R.id.edit_fecha_nota);
        EditText edit_nota = vista_dialog.findViewById(R.id.edit_nota);

        builder.setPositiveButton("Agregar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date fecha = new Date();
                        String fecha_str = edit_fecha_nota.getText().toString().trim();
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        try{
                            fecha = formato.parse(fecha_str);
                        }catch (Exception e){

                        }
                        String nota = edit_fecha_nota.getText().toString().trim();
                        String color = edit_nota.getText().toString().trim();
                        if(nota.length()>0){
                            createNota(fecha, nota);
                        }
                        else{
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Debe escribir una nota",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createNota(Date fecha, String nota){
        realm.beginTransaction();
        ModelAnimalNotas xnota = new ModelAnimalNotas(fecha, nota);
        realm.copyToRealm(xnota);
        animal.getNotas().add(xnota);
        realm.commitTransaction();
    }
}