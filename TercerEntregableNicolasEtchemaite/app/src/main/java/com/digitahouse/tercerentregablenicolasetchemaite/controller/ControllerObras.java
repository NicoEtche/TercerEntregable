package com.digitahouse.tercerentregablenicolasetchemaite.controller;

import android.net.Uri;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOObrasStorage;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOobras;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

import java.net.URI;

public class ControllerObras {

    public void getObras(final ResultListener<ObraContainer> obraContainerResultListener){
        DAOobras dAOobras = new DAOobras();

        dAOobras.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
                obraContainerResultListener.finish(result);
            }
        });
    }

    public void getImageFromStorage(String string, final ResultListener<Uri> listener){
        DAOObrasStorage daoObrasStorage = new DAOObrasStorage();

        daoObrasStorage.getImageFromStorage(string, new ResultListener<Uri>() {
            @Override
            public void finish(Uri result) {
                listener.finish(result);
            }
        });
    }
}
