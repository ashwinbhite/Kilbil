package com.example.ashtech.kilbil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ashtech.kilbil.model.ImageUpload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button upload, choose;
    private ImageView imageView;
    private EditText edtImgDesc;
    private File uploadFile;
    private String url;

    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private StorageReference storageReference;
    private DatabaseReference mDatabaseImgRef;
    private final int requestCode = 234;
    private byte[] byteImg;
    private String extr;
    private File savedfile;
    Uri uri;
    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        upload = (Button) findViewById(R.id.buttonUpload);
        choose = (Button) findViewById(R.id.buttonChoose);
        imageView = (ImageView) findViewById(R.id.imageView);
        edtImgDesc = (EditText) findViewById(R.id.img_desc);

        File root = new File(Environment.getExternalStorageDirectory() + File.separator + "myDir" + File.separator);
        root.mkdir();
        uploadFile = new File(root, "fireuploadimg");


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseImgRef = FirebaseDatabase.getInstance().getReference().child(FB_DATABASE_PATH);

        upload.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("inside activity result req code=" + requestCode + "res code=" + resultCode);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {

            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
                imageView.setVisibility(View.VISIBLE);
                uri = imageUri;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onClick(View view) {
        //if the clicked btn_menu_sansthan is upload
        if (view == upload) {
            uploadFile();
        }
    }

    private void uploadFile() {
        //if there is a file to upload
        if (uri != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.setCancelable(false);
            progressDialog.show();

//            Uri file = Uri.fromFile(savedfile);
            //File file = new File(getPathFromURI(uri));
//            System.out.println("file.getLastPathSegment() "+file.getLastPathSegment());
//            StorageReference riversRef = storageReference.child("images/"+file.getLastPathSegment());

            StorageReference riversRef = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis() + ".jpg");
            riversRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //if the upload is successfull hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                            ImageUpload imageUpload;
                            if(edtImgDesc.getText().toString().trim().length()>0){
                             imageUpload = new ImageUpload(taskSnapshot.getDownloadUrl().toString(),edtImgDesc.getText().toString().trim());
                            }else{
                                imageUpload = new ImageUpload(taskSnapshot.getDownloadUrl().toString(),"");
                            }

                            //Save image info in to firebase database
                            String uploadId = mDatabaseImgRef.push().getKey();
                            mDatabaseImgRef.child(uploadId).setValue(imageUpload);
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
            Toast.makeText(getApplicationContext(), "Please select an image.", Toast.LENGTH_SHORT).show();
        }
    }

}

