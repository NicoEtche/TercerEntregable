package com.digitahouse.tercerentregablenicolasetchemaite.controller;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.AppDataBase;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Artist;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;
import com.google.gson.internal.bind.ObjectTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ControllerRoom {

    private AppDataBase appDB = null;
    private Context context;

    public ControllerRoom(Context context) {
        this.appDB = Room.databaseBuilder(context, AppDataBase.class, "ArtApp").allowMainThreadQueries().build();
        this.context = context;
    }

    public void getAllObras(List<Obra> obras) {

 obras.addAll(appDB.daoDatabaseAcces().getAllObras());

    }

    public void insertObra(Obra obra) {

        appDB.daoDatabaseAcces().insertObra(obra);

    }

    public void getAllArtists(List<Artist> artists) {
       if (!Helper.isOnlineNet()) artists.addAll(appDB.daoDatabaseAcces().getAllArtists());
    }

    public void insertArtistToDB(Artist artist) {

        appDB.daoDatabaseAcces().insertArtist(artist);
    }


}
