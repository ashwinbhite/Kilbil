package com.example.ashtech.kilbil;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ashtech on 07/10/16.
 *
 */

@IgnoreExtraProperties
public class User {

    public String homework;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String homework) {

        this.homework = homework;
    }
}