package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Artist;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;

import java.util.List;
@Dao
public interface DAODatabaseAcces {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObra(Obra... obras);

    @Query("SELECT * FROM obras")
    List<Obra> getAllObras();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArtist(Artist... artist);

    @Query("SELECT * FROM artistas")
    List<Artist> getAllArtists();

















}
