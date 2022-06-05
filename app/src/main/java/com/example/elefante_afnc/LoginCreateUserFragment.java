package com.example.elefante_afnc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginCreateUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginCreateUserFragment extends Fragment {
    private FirebaseAuth mAuth;
    private EditText edit_create_user;
    private EditText edit_create_password;
    private Button btn_create_user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginCreateUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginCreateUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginCreateUserFragment newInstance(String param1, String param2) {
        LoginCreateUserFragment fragment = new LoginCreateUserFragment();
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
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_login_create_user, container, false);
        edit_create_user = view.findViewById(R.id.edit_create_user);
        edit_create_password = view.findViewById(R.id.edit_create_password);
        btn_create_user = view.findViewById(R.id.btn_create_user);

        btn_create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_create_user.getText().toString().trim();
                String password = edit_create_password.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getActivity(), "Usuario creado", Toast.LENGTH_SHORT).show();
                                }else{
                                    Log.d("tag",
                                            "Error al crear usuario",
                                            task.getException());
                                    Toast.makeText(getActivity(), "Error al crear usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        return view;
    }
}