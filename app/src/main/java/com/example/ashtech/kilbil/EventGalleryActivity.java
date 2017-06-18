package com.example.ashtech.kilbil;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class EventGalleryActivity extends AppCompatActivity {
    private StorageReference storage = FirebaseStorage.getInstance().getReference();
    File localFile = null;
    private StorageReference mStorageRef;
    private ImageView imageView;
    public static final String FB_DATABASE_PATH = "image";
    public static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_gallery);
        imageView = (ImageView) findViewById(R.id.img_gallery_view);
        StorageReference storageReference = storage.child("images/pic.jpg");



        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                System.out.println("Images downloaded successfully !");
                imageView.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                System.out.println("Error downloading image");
            }
        });

    }
    public void btnShowListImage_Click(View v) {
        Intent i = new Intent(EventGalleryActivity.this, ImageListActivity.class);
        startActivity(i);
    }
}
