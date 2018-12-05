package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class DAOPhoto {
    private FirebaseStorage storage = FirebaseStorage.getInstance();


    public void uploadImageToStorage(byte[] bytes){
        //Accedo a la raiz del directorio
        StorageReference referenciaRaiz = storage.getReference();
        //
        StorageReference referenciaCarpetaImagenes = referenciaRaiz.child("imagenes");
        //
        final StorageReference referenciaCarpetaUsuario = referenciaCarpetaImagenes.child(new Date().getTime() + ".jpg");
        //
        referenciaCarpetaUsuario.putBytes(bytes).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                task.getResult();
            }
        });


    }
}
