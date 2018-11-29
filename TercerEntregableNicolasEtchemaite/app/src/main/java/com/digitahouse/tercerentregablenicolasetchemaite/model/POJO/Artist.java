package com.digitahouse.tercerentregablenicolasetchemaite.model.POJO;

import java.io.Serializable;

public class Artist implements Serializable {

    private String Influenced_by;
    private String artistId;
    private String name;
    private String nationality;

    public Artist() {
    }

    public String getInfluenced_by() {
        return Influenced_by;
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
}
