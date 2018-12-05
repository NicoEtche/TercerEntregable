package com.digitahouse.tercerentregablenicolasetchemaite.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerViewObras.ListenerRecyclerObras, FragmentObras.ListenerStartCamera{

   protected Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);


        transactionDoReplace(R.id.container_main, new FragmentObras());


    }

    public void transactionDoReplace(Integer container, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container_main, fragment);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("flag", 1);
                startActivity(intent);
                return true;
            case R.id.button_chat:
                Intent intentChat = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intentChat);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void openObraData(Obra obra) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(FragmentDetalle.CLAVE_OPENOBRA, obra);
        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        fragmentDetalle.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_main, fragmentDetalle);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    @Override
    public void startCamera() {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
    }
}
