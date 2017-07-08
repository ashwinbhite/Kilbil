package com.example.ashtech.kilbil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLL;
    Button btn_about_us;
    Button btn_Schedule;
    Button btn_feedback;
    Button btn_TeacherLogin;
    Button btn_diary;
    Button btn_EventGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.inlogo);
       // getActionBar().setTitle("KILBIL");
        getSupportActionBar().setTitle("KIlBIL");

        mainLL= (LinearLayout) findViewById(R.id.mainLL);

        btn_about_us= (Button) findViewById(R.id.btn_menu_sansthan);
        btn_Schedule= (Button) findViewById(R.id.btn_menu_homework);
        btn_feedback= (Button) findViewById(R.id.btn_menu_feedback);
        btn_TeacherLogin = (Button) findViewById(R.id.btn_menu_login);
        btn_diary= (Button) findViewById(R.id.btn_menu_diary);
        btn_EventGallery= (Button) findViewById(R.id.btn_menu_gallery);

        btn_about_us.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent myIntent =new Intent(MainActivity.this,SansthaActivity.class);
                startActivity(myIntent);

            }

        });
        btn_TeacherLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){

                Intent myIntent =new Intent(MainActivity.this,TeacherLoginActivity.class);
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

        btn_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,
                        DiaryActivity.class);
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

        btn_EventGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ImageListActivity.class);
                startActivity(i);
            }
        });


    }
}
