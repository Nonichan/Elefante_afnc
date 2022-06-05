package com.example.elefante_afnc;



import static io.realm.Realm.getApplicationContext;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment
    implements View.OnClickListener
{
    private TextView texto_muestra;
    private String logeado;
    private FirebaseAuth mAuth;
    private EditText edit_user;
    private EditText edit_password;
    private Button btn_login;
    private Button btn_cerrar_sesion;
    private Button btn_crear_cuenta;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {

            view = inflater.inflate(R.layout.fragment_login, container, false);
            edit_user = view.findViewById(R.id.edit_user);
            edit_password = view.findViewById(R.id.edit_password);
            btn_login = view.findViewById(R.id.btn_login);
            btn_crear_cuenta = view.findViewById(R.id.btn_crear_cuenta);
            btn_crear_cuenta.setOnClickListener(this);
            mAuth = FirebaseAuth.getInstance();
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = edit_user.getText().toString().trim();
                    String password = edit_password.getText().toString().trim();

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(),
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Navigation.findNavController(view).navigate(R.id.action_nav_login_to_nav_mis_animales);
                                            } else {
                                                Log.d("Tag", "Error al logear",
                                                        task.getException());
                                                Toast.makeText(getActivity(), "Error al logear", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                }
            });
        }else{
            view = inflater.inflate(R.layout.fragment_login_conectado, container, false);
            btn_cerrar_sesion = view.findViewById(R.id.boton_cerrar_sesion);
            btn_cerrar_sesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Cerrando Sesion", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    Navigation.findNavController(view).navigate(
                            R.id.action_nav_login_to_nav_lista_animales_main_Fragment
                    );
                }
            });
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        Navigation.findNavController(view)
                .navigate(R.id.action_nav_login_to_loginCreateUserFragment);
    }
}