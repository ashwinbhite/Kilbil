package com.example.ashtech.kilbil;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLL;
    Button btn_Schedule;
    Button btn_feedback;
    Button btn_aboutus;
    Button btn_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLL= (LinearLayout) findViewById(R.id.mainLL);

        btn_aboutus= (Button) findViewById(R.id.button);
        btn_Schedule= (Button) findViewById(R.id.button4);
        btn_feedback= (Button) findViewById(R.id.button6);
        btn_gallery = (Button) findViewById(R.id.btn_gallery);

        btn_aboutus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                AboutUsFragment aboutUsFragment =new AboutUsFragment();
                android.app.FragmentManager fm= getFragmentManager();
                fm.beginTransaction().add(R.id.activity_main,aboutUsFragment,"AboutUsFragment").commit();
                mainLL.setVisibility(View.GONE);

            }

        });
        btn_gallery.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){

                Intent myIntent =new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(myIntent);

            }

        });

        btn_Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,
                        HomeworkActivity.class);
                startActivity(myIntent);
            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,
                        FeedbackActivity.class);
                startActivity(myIntent);
            }
        });



    }
}
