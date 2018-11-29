package com.digitahouse.tercerentregablenicolasetchemaite.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerObras;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentObras extends Fragment {

    private AdapterRecyclerViewObras adapterRecyclerViewObras;


    public FragmentObras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_obras, container, false);
        adapterRecyclerViewObras = new AdapterRecyclerViewObras();
        getObras();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragment_obras);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecyclerViewObras);

        return view;

    }

    public void getObras(){
        ControllerObras controller = new ControllerObras();

        controller.getObras(new ResultListener<ObraContainer>() {
            @Override
            public void finish(ObraContainer result) {
               adapterRecyclerViewObras.addListObras(result.getPaints());
            }
        });
    }

}
