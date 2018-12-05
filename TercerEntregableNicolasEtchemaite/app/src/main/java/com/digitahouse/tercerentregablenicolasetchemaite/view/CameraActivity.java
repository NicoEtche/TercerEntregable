package com.digitahouse.tercerentregablenicolasetchemaite.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerPhoto;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOPhoto;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class CameraActivity extends AppCompatActivity {

    public static final Integer CAMERA_REQUEST_CODE = 1;
    public static final Integer CAMERA_PERMISSION_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        requestPermission();


    }

    private void showCamera() {
        EasyImage.openCamera(this, CAMERA_REQUEST_CODE);
    }


    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showCamera();
        } else {
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                Toast.makeText(CameraActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onImagesPicked(List<File> imagesFiles, EasyImage.ImageSource source, int type) {


                File file = imagesFiles.get(0);
                Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                Toast.makeText(CameraActivity.this, "Conseguida", Toast.LENGTH_SHORT).show();

                ControllerPhoto controllerPhoto = new ControllerPhoto();
                controllerPhoto.uploadImageToStorage(convertirImagenABytes(myBitmap));

                onBackPressed();

            }


            public byte[] convertirImagenABytes(Bitmap bitmap) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();
                return data;
            }
        });
    }

}

