package com.digitahouse.tercerentregablenicolasetchemaite.view;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerArtistas;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerObras;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Artist;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {

    public static final String CLAVE_OPENOBRA = "obra";

    private List<Artist> artists;
    private String artistId;
    private Obra obra;
    private Artist artist;
    private TextView artista;
    private TextView pais;


    public FragmentDetalle() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        TextView nombreDeLaObra = view.findViewById(R.id.text_view_nombre_de_la_obra);
        artista = view.findViewById(R.id.text_view_artista);
        ImageView fotoObra = view.findViewById(R.id.image_view_obra_detalle);
        pais = view.findViewById(R.id.text_view_pais_de_origen);

        Bundle bundle = getArguments();
        obra = (Obra) bundle.getSerializable(CLAVE_OPENOBRA);

        nombreDeLaObra.setText(obra.getName());
        artistId = obra.getArtistId();

        getArtists();

        String realPath = obra.getImage().substring(13,obra.getImage().length());


        getImageFromStorage(realPath, fotoObra, getContext());




        return view;
    }

    public void getArtists() {
        ControllerArtistas controllerArtistas = new ControllerArtistas();

        controllerArtistas.getArtists(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> result) {

                artists = result;
                artist = detectArtist(artistId);
                artista.setText("Artist: "+artist.getName());
                pais.setText("País: "+artist.getNationality());


            }
        });
    }

    public Artist detectArtist(String artistId) {

        Artist elArtista = new Artist();

        for (Artist artist : artists) {
            if (artist.getArtistId().equals(artistId)) {
                elArtista = artist;
            }
        }

        return elArtista;
    }
    public void getImageFromStorage(String string, final ImageView imageView, final Context context) {
        ControllerObras controllerObras = new ControllerObras();
        controllerObras.getImageFromStorage(string, new ResultListener<Uri>() {
            @Override
            public void finish(Uri result) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.placeholder).fitCenter();
                requestOptions.error(R.drawable.placeholder);
                requestOptions.fallback(R.drawable.placeholder);

                Glide.with(context).applyDefaultRequestOptions(requestOptions).load(result)
                        .transition(DrawableTransitionOptions.withCrossFade()).into(imageView);

            }
        });

    }
}
