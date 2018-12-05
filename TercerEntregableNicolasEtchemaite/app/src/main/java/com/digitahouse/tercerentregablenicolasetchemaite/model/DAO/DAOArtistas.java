package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Artist;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.digitahouse.tercerentregablenicolasetchemaite.view.MainActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DAOArtistas  {

    private FirebaseDatabase database;

    public DAOArtistas() {
    }

    public void getArtsits(final ResultListener<List<Artist>> listResultListener) {

        database = FirebaseDatabase.getInstance();
        DatabaseReference dataBaseReference = database.getReference();
        DatabaseReference referenciaAArtistas = dataBaseReference.child("artists");
        referenciaAArtistas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Artist> artists = new ArrayList<>();

                for (DataSnapshot artist : dataSnapshot.getChildren()) {
                    artists.add(artist.getValue(Artist.class));
                }
                listResultListener.finish(artists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listResultListener.finish(new ArrayList<Artist>());
            }
        });
    }
}
