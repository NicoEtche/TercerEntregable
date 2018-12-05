package com.digitahouse.tercerentregablenicolasetchemaite.controller;

import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOPhoto;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

public class ControllerPhoto {

    public void uploadImageToStorage(byte[] image) {
        DAOPhoto daoPhoto = new DAOPhoto();

        daoPhoto.uploadImageToStorage(image);
    }
}
