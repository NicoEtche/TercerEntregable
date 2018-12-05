package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.arch.persistence.room.Insert;

import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET(Helper.BASE_URL)
    Call<ObraContainer> getObras();




}
