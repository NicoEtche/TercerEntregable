package com.digitahouse.tercerentregablenicolasetchemaite.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerObras;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerRoom;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentObras extends Fragment  {

    private AdapterRecyclerViewObras adapterRecyclerViewObras;
    private List<Obra> paints;

    public FragmentObras() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        paints = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_obras, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floating_button_camera);
        adapterRecyclerViewObras = new AdapterRecyclerViewObras(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragment_obras);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapterRecyclerViewObras);
            getObras(getContext(), paints);

            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
ListenerStartCamera listenerStartCamera = (ListenerStartCamera) getContext();
listenerStartCamera.startCamera();
                }
            });
        return view;

    }



    public void getObras(Context context, List<Obra> obras) {
        final ControllerObras controller = new ControllerObras();

        controller.getObras(new ResultListener<ObraContainer>(){
            @Override
            public void finish(ObraContainer result) {
                paints = result.getPaints();
                for (Obra obra : paints) {
                    String fullPath = obra.getImage().substring(13, obra.getImage().length());
                    obra.setImage(fullPath);
                    getImageFromStorage(obra.getImage(), obra);
                }
            }
        },context, obras, adapterRecyclerViewObras);



    }




    public void getImageFromStorage(String string, final Obra obra) {
        ControllerObras controllerObras = new ControllerObras();
        controllerObras.getImageFromStorage(string, new ResultListener<Uri>() {
            ControllerRoom controllerRoom = new ControllerRoom(getContext());
            @Override
            public void finish(Uri result) {
                obra.setFullLink(result.toString());
                adapterRecyclerViewObras.addObraToList(obra);
                controllerRoom.insertObra(obra);
            }
        });

    }

    public interface ListenerStartCamera{
        public void startCamera();
    }




}








