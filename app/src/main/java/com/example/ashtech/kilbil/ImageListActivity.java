package com.example.ashtech.kilbil;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {

    private List<ImageUpload> list;
    private ListView lv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;
    private StorageReference storageReference;
    private File localFile;
    private ImageView imageView;
    private DatabaseReference mDatabaseImgRef;
    public static final String FB_DATABASE_PATH = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        list = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listViewImage);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait loading images");
        progressDialog.show();

        mDatabaseImgRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);


        mDatabaseImgRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //com.example.ashtech.kilbil.ImageUpload class require default constructor
                    ImageUpload img = snapshot.getValue(ImageUpload.class);
                    list.add(img);
                }


                //Init adapter
                adapter = new ImageListAdapter(ImageListActivity.this, R.layout.image_item, list);
                //Set adapter for listview
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });

    }
}
