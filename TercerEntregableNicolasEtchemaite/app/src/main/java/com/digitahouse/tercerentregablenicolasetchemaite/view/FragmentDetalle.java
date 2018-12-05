package com.digitahouse.tercerentregablenicolasetchemaite.view;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerArtistas;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerObras;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerRoom;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.AppDataBase;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Artist;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.util.Helper;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private ImageView fotoObra;


    public FragmentDetalle() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        artists = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        TextView nombreDeLaObra = view.findViewById(R.id.text_view_nombre_de_la_obra);
        artista = view.findViewById(R.id.text_view_artista);
        fotoObra = view.findViewById(R.id.image_view_obra_detalle);
        pais = view.findViewById(R.id.text_view_pais_de_origen);

        Bundle bundle = getArguments();
        obra = (Obra) bundle.getSerializable(CLAVE_OPENOBRA);

        nombreDeLaObra.setText(obra.getName());
        artistId = obra.getArtistId();

        getArtistsFromDB();
        getImageFromStorage(obra.getImage());
        getArtists();





        return view;
    }

    public void getArtists() {

        ControllerArtistas controllerArtistas = new ControllerArtistas();
        controllerArtistas.getArtists(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> result) {

                ControllerRoom controllerRoom = new ControllerRoom(getContext());
                artists.addAll(result);
                setArtistData();

                for (Artist artist : result) {
                    controllerRoom.insertArtistToDB(artist);
                }
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


    public void getImageFromStorage(final String string) {
        ControllerObras controllerObras = new ControllerObras();
        controllerObras.getImageFromStorage(string, new ResultListener<Uri>() {
            @Override
            public void finish(Uri result) {

                Glide.with(getContext()).load(result.toString()).into(fotoObra);


            }
        });

    }
public void getArtistsFromDB(){
        ControllerRoom controllerRoom = new ControllerRoom(getContext());
        controllerRoom.getAllArtists(artists);
        if (artists.size()>0) {
           setArtistData();
        }

}

    public void setArtistData() {

        artist = detectArtist(artistId);
        artista.setText("Artist: " + artist.getName());
        pais.setText("País: " + artist.getNationality());


    }


}
