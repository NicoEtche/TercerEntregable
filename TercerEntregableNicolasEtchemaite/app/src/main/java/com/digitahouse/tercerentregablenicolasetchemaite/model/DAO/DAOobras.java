package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.widget.Toast;

import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.digitahouse.tercerentregablenicolasetchemaite.view.MainActivity;
import com.google.firebase.storage.FirebaseStorage;

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

}
