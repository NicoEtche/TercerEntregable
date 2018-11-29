package com.digitahouse.tercerentregablenicolasetchemaite.view;




import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistrarse extends android.support.v4.app.Fragment {

    private FirebaseAuth mAuth;
    private View view;





    public FragmentRegistrarse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registrarse, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button aceptar = view.findViewById(R.id.button_aceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText user = view.findViewById(R.id.edit_text_usuario_reg);
                TextInputEditText password = view.findViewById(R.id.edit_text_contraseña_reg);
                mAuth.createUserWithEmailAndPassword(user.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Cuenta creada", Toast.LENGTH_SHORT).show();                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                    }
                });
            }
        });

    return view;
    }

    private void updateUI(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(this);
        transaction.commit();

    }

}
