package com.example.elefante_afnc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisAnimalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisAnimalesFragment extends Fragment implements RealmChangeListener<RealmResults<ModelAnimal>> {



    private Realm realm;
    private FloatingActionButton fab;
    private GridView  gridView;
    private RealmResults <ModelAnimal> animales;
    private misAnimalesAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MisAnimalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisAnimalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisAnimalesFragment newInstance(String param1, String param2) {
        MisAnimalesFragment fragment = new MisAnimalesFragment();
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
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        String menu_title = animales.get(info.position).getNombre();
        menu.setHeaderTitle(menu_title);
        getActivity().getMenuInflater().inflate(R.menu.mis_animales_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();        switch (item.getItemId()){
            case R.id.eliminar_animal:
                deleteAnimal(animales.get(info.position));
                return true;

            case R.id.editar_animal:
                showAlertEdit("Editar Animal", "Cambio los datos del animal",
                        animales.get(info.position));
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mis_animales, container, false);

        gridView = view.findViewById(R.id.gridView);
        fab = view.findViewById(R.id.fab);
        realm = Realm.getDefaultInstance();
        animales = realm.where(ModelAnimal.class).findAll();
        animales.addChangeListener(this);

        adapter = new misAnimalesAdapter(getActivity(),
                animales, R.layout.item_grid_mis_animales);
        gridView.setAdapter(adapter);
        registerForContextMenu(gridView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertAdd("Agrega un animal", "Escribe los datos del animal");
            }
        });

        return view;
    }

    public void showAlertAdd(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);
        View vista_dialog = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_animales, null);
        builder.setView(vista_dialog);
        EditText edit_nombre_animal = vista_dialog.findViewById(R.id.edit_nombre_animal);
        EditText edit_color_animal = vista_dialog.findViewById(R.id.edit_color_animal);

        builder.setPositiveButton("Agregar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nombre = edit_nombre_animal.getText().toString().trim();
                        String color = edit_color_animal.getText().toString().trim();
                        if(nombre.length()>0){
                            createAnimal(nombre, color);
                        }
                        else{
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Debe escribir almenos un caracter",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createAnimal(String nombre, String color){
        realm.beginTransaction();
        ModelAnimal animal = new ModelAnimal(nombre, color);
        realm.copyToRealm(animal);
        realm.commitTransaction();
    }

    private void deleteAnimal(ModelAnimal animal){
        realm.beginTransaction();
        animal.deleteFromRealm();
        realm.commitTransaction();
    }

    private void editAnimal(String newNombre, String newColor, ModelAnimal animal){
        realm.beginTransaction();
        animal.setNombre(newNombre);
        animal.setColor(newColor);
        realm.copyToRealmOrUpdate(animal);
        realm.commitTransaction();

    }

    @Override
    public void onChange(RealmResults<ModelAnimal> modelAnimals) {
        adapter.notifyDataSetChanged();
    }

    public void showAlertEdit(String title, String message, ModelAnimal animal) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);
        View vista_dialog = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_animales, null);
        builder.setView(vista_dialog);
        EditText edit_nombre_animal = vista_dialog.findViewById(R.id.edit_nombre_animal);
        EditText edit_color_animal = vista_dialog.findViewById(R.id.edit_color_animal);
        edit_nombre_animal.setText(animal.getNombre());
        edit_color_animal.setText(animal.getColor());

        builder.setPositiveButton("Guardar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nombre = edit_nombre_animal.getText().toString().trim();
                        String color = edit_color_animal.getText().toString().trim();
                        if(nombre.length()>0){
                            editAnimal(nombre, color, animal);
                        }
                        else{
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Debe escribir almenos un caracter",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}