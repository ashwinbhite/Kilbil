package com.example.ashtech.kilbil;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GalleryActivity extends AppCompatActivity {


    Button btnUpload;
    Button btnFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        btnUpload= (Button) findViewById(R.id.btnUpload);
        btnFetch= (Button) findViewById(R.id.fetch);

        btnUpload.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){

                Intent myIntent = new Intent(GalleryActivity.this,UploadActivity.class);
                GalleryActivity.this.startActivity(myIntent);

            }

        });
        btnFetch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){

                Intent myIntent1 = new Intent(GalleryActivity.this,DemoActivity.class);
                GalleryActivity.this.startActivity(myIntent1);

            }

        });

    }
}
