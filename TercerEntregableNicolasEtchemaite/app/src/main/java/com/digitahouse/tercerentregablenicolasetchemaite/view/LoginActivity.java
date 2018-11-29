package com.digitahouse.tercerentregablenicolasetchemaite.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText usuario;
    private TextInputEditText contraseña;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker accessTokenTracker;
    private int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        printHash();
        TextView registrate = findViewById(R.id.text_view_registrate);
        mAuth = FirebaseAuth.getInstance();
        Button ingresar = findViewById(R.id.button_ingresar);
        LoginButton loginButton = findViewById(R.id.facebook_login_button);
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                // ...
            }
        });
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    Toast.makeText(LoginActivity.this, "Signing out", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    accessTokenTracker.stopTracking();
                }
            }
        };

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usuario = findViewById(R.id.edit_text_usuario);
                contraseña = findViewById(R.id.edit_text_contraseña);
                if (!usuario.equals(null) && !contraseña.equals(null)) {
                    mAuth.signInWithEmailAndPassword(usuario.getText().toString(), contraseña.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(LoginActivity.this, "Has iniciado sesion", Toast.LENGTH_SHORT).show();


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(LoginActivity.this, "Datos erroneos, vuelve a intentar",
                                                Toast.LENGTH_SHORT).show();
                                        usuario.setText("");
                                        contraseña.setText("");
                                    }

                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this, "Ingrese sus datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.container_login, new FragmentRegistrarse());
                transaction.commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            updateUI(user);
                            startActivityAMainSinBackStab();


                        } else {

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

    public void updateUI(FirebaseUser user) {
        if (user != null && AccessToken.getCurrentAccessToken() != null) {
            accessTokenTracker.startTracking();
        }

    }

    private void printHash() {
        try {

            PackageInfo info =
                    getPackageManager().getPackageInfo(getApplicationContext().getPackageName(),
                            PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.v("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null && AccessToken.getCurrentAccessToken() != null) {
            updateUI(mAuth.getCurrentUser());

            if (getIntent() != null) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                flag = bundle.getInt("flag");
            }
            if (flag != 1) {
                startActivityAMainSinBackStab();
                flag = 0;
            }
        }
    }

    public void startActivityAMainSinBackStab() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        finish();
    }
}
