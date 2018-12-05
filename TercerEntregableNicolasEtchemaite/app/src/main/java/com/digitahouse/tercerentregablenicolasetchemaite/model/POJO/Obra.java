package com.digitahouse.tercerentregablenicolasetchemaite.model.POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "Obras")
public class Obra implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "nombre")
    private String name;
    @ColumnInfo(name = "id")
    private String artistId;
    @ColumnInfo(name = "imagePath")
    private String image;
    @ColumnInfo(name = "fullLink")
    private String fullLink;

    public Obra() {
    }

    @Ignore
    public Obra(@NonNull String name, String artistId, String image) {
        this.name = name;
        this.artistId = artistId;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setFullLink(String fullLink) {
        this.fullLink = fullLink;
    }

    public String getFullLink() {
        return fullLink;
    }
}
