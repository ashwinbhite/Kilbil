package com.example.ashtech.kilbil;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import com.example.ashtech.kilbil.R;
/**
 * Created by Ashtech on 1/28/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private VideoView mVideoView2;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        if (getIntent().getExtras() != null) {
//            Toast.makeText(this, getIntent().getExtras().getString("notification.body"), Toast.LENGTH_LONG).show();
//        }

       TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
               /* if (getIntent().getExtras() != null) {

                    intent.putExtra("notification.body", getIntent().getExtras().getString("notification.body"));
                }*/
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 2500);


    }

    @Override
    protected void onStop() {
        super.onStop();
        //mVideoView2.stopPlayback();

    }
}
