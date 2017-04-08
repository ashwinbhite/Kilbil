package com.example.ashtech.kilbil;

import android.app.Application;

import com.firebase.client.Firebase;


/**
 * Created by Pawan on 2/3/2017.
 */

public class FireApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);

    }
}
