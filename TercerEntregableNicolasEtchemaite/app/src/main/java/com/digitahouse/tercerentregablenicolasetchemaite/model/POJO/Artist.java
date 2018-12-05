package com.digitahouse.tercerentregablenicolasetchemaite.model.POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
@Entity(tableName = "artistas")
public class Artist implements Serializable {



    @NonNull
    @PrimaryKey
    private String artistId;

    @ColumnInfo(name = "nombre")
    private String name;
    @ColumnInfo(name = "pais")
    private String nationality;

    public Artist() {
    }


    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }



    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
