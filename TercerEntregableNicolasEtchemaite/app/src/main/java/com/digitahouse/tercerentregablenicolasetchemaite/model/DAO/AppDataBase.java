package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Artist;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;

@Database(entities = {Obra.class, Artist.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DAODatabaseAcces daoDatabaseAcces();
}
