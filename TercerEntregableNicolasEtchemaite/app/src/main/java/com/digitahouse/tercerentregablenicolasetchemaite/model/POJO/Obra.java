package com.digitahouse.tercerentregablenicolasetchemaite.model.POJO;

import android.net.Uri;

import java.io.Serializable;

public class Obra implements Serializable {


    private String name;
    private String artistId;
    private String image;


    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }
}
