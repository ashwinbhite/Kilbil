package com.example.ashtech.kilbil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_Schedule;
    Button btn_feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Schedule= (Button) findViewById(R.id.button4);
        btn_feedback= (Button) findViewById(R.id.button6);

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
