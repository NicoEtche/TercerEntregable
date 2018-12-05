package com.digitahouse.tercerentregablenicolasetchemaite.model.POJO;

public class Message {
    private String id;
    private String contenido;
    private String name;
    private boolean isMine;

    public Message(){}

    public Message(String id, String contenido, String name) {
        this.id = id;
        this.contenido = contenido;
        this.name = name;
    }

    public String getContenido() {
        return contenido;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return contenido;
    }

    public void setIsMine(boolean mine) {
        isMine = mine;
    }

    public boolean getIsMine() {
        return isMine;
    }
}
