package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DAOObrasStorage {
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    public void getImageFromStorage(String string, final ResultListener<Uri> listener) {

        StorageReference reference = storage.getReference();
        String path = "images/" + string;
        StorageReference referenceImages = reference.child(path);


        referenceImages.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                listener.finish(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                listener.finish(null);

            }
        });
    }


}
