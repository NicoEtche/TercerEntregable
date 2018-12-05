package com.digitahouse.tercerentregablenicolasetchemaite.controller;

import android.content.Context;

import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOArtistas;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Artist;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

import java.util.List;

public class ControllerArtistas {

    public void getArtists(final ResultListener<List<Artist>> listResultListener) {
        if (Helper.isOnlineNet()) {
            DAOArtistas daoArtistas = new DAOArtistas();

            daoArtistas.getArtsits(new ResultListener<List<Artist>>() {
                @Override
                public void finish(List<Artist> result) {
                    listResultListener.finish(result);
                }
            });
        } }



}
