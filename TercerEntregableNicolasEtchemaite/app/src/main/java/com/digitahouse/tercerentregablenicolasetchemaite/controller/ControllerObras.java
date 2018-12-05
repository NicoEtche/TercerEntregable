package com.digitahouse.tercerentregablenicolasetchemaite.controller;

import android.content.Context;
import android.net.Uri;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOObrasStorage;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOobras;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.digitahouse.tercerentregablenicolasetchemaite.view.AdapterRecyclerViewObras;

import java.net.URI;
import java.util.List;

public class ControllerObras {

    public void getObras(final ResultListener<ObraContainer> obraContainerResultListener, Context context,
                         List<Obra> obras, AdapterRecyclerViewObras adapterRecyclerViewObras) {
       ControllerRoom controllerRoom = new ControllerRoom(context);

        if (Helper.isOnlineNet()) {
            DAOobras dAOobras = new DAOobras();

            dAOobras.getObras(new ResultListener<ObraContainer>() {
                @Override
                public void finish(ObraContainer result) {
                    obraContainerResultListener.finish(result);

                }
            });
        }else{
            controllerRoom.getAllObras(obras);
            adapterRecyclerViewObras.addListObras(obras);


        }
    }




    public void getImageFromStorage(String string, final ResultListener<Uri> listener){
           if (Helper.isOnlineNet()) {
               DAOObrasStorage daoObrasStorage = new DAOObrasStorage();

               daoObrasStorage.getImageFromStorage(string, new ResultListener<Uri>() {
                   @Override
                   public void finish(Uri result) {
                       listener.finish(result);
                   }
               });
           }

    }



}
