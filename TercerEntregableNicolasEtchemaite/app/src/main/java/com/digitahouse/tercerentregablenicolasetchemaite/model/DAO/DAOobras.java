package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.arch.persistence.room.Entity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.digitahouse.tercerentregablenicolasetchemaite.view.MainActivity;
import com.google.firebase.storage.FirebaseStorage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DAOobras extends RetrofitApp {

    private Service service;

    public DAOobras() {
        super(Helper.BASE_URL);
        this.service = retrofit.create(Service.class);
    }

    public void getObras(final ResultListener<ObraContainer> obraContainerResultListener){
        Call<ObraContainer> call = service.getObras();

        call.enqueue(new Callback<ObraContainer>() {
            @Override
            public void onResponse(Call<ObraContainer> call, Response<ObraContainer> response) {

                ObraContainer obraContainer = response.body();
                obraContainerResultListener.finish(obraContainer);
            }

            @Override
            public void onFailure(Call<ObraContainer> call, Throwable t) {

            }
        });
    }
    public void downloadImageFromPath(String path, ImageView imageView){
        InputStream in =null;
        Bitmap bmp=null;
        int responseCode = -1;
        try{

            URL url = new URL(path);//"http://192.xx.xx.xx/mypath/img1.jpg
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //download
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageBitmap(bmp);
            }

        }
        catch(Exception ex){
            Log.e("Exception",ex.toString());
        }
    }

}
