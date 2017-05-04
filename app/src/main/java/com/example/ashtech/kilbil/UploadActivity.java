package com.example.ashtech.kilbil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button upload,choose;
    ImageView imageView;
    private File uploadFile;


    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private StorageReference storageReference;
    private final int requestCode = 234;
    private byte[] byteImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        upload = (Button) findViewById(R.id.buttonUpload);
        choose = (Button) findViewById(R.id.buttonChoose);
        imageView = (ImageView) findViewById(R.id.imageView);

        File root = new File(Environment.getExternalStorageDirectory()+File.separator+"myDir"+File.separator);
        root.mkdir();
        uploadFile = new File(root,"fireuploadimg");

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri outputfileUri = Uri.fromFile(uploadFile);
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                photoCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT,outputfileUri);
                startActivityForResult(Intent.createChooser(photoCaptureIntent, "Select an Image"), PICK_IMAGE_REQUEST);
            }
        } );

        storageReference= FirebaseStorage.getInstance().getReference();

        upload.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("inside activity result req code="+requestCode +"res code="+resultCode);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null) {
            Bundle b = data.getExtras();
            filePath = Uri.parse(data.toUri(0));
            System.out.println("Filepath="+filePath);
            Bitmap bitmap = (Bitmap) b.get("data");
            imageView.setImageBitmap(bitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100, baos);
            byteImg = baos.toByteArray();

        }

    }

    @Override
    public void onClick(View view) {
        //if the clicked button is upload
        if (view == upload) {
            uploadFile();
        }
    }

    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = storageReference.child("images/pic.jpg");
            riversRef.putBytes(byteImg)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            Toast.makeText(getApplicationContext(),"No filepath", Toast.LENGTH_SHORT).show();
        }
    }
}

