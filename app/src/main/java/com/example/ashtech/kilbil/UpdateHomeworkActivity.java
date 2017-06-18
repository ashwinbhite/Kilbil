package com.example.ashtech.kilbil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateHomeworkActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private EditText input;
    private EditText et_date;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseHWDatabase;
    private TextView txtClass;
    private String userId;
    private String homeworkString, dateString;
    private long maxId;
    private String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_homework);


        txtClass = (TextView) findViewById(R.id.txt_class_name);
        input = (EditText) findViewById(R.id.homework);
        btnSave = (Button) findViewById(R.id.btn_upload);
        et_date = (EditText) findViewById(R.id.etDate);


        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'homework' node
        mFirebaseHWDatabase = mFirebaseInstance.getReference("Homework");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        userEmail = getIntent().getStringExtra("userEmail");
        switch (userEmail) {
            case "teacher1@kilbil.com":
                mFirebaseHWDatabase = mFirebaseHWDatabase.child("kg1");
                txtClass.setText("KG I");
                break;
            case "teacher2@kilbil.com":
                mFirebaseHWDatabase = mFirebaseHWDatabase.child("kg2");
                txtClass.setText("KG II");

                break;
            case "teacher3@kilbil.com":
                mFirebaseHWDatabase = mFirebaseHWDatabase.child("nursery");
                txtClass.setText("Nursery");
                break;
            case "teacher4@kilbil.com":
                mFirebaseHWDatabase = mFirebaseHWDatabase.child("prenursery");
                txtClass.setText("Pre-Nursery");
                break;

        }




        // Initializing a String Array
        String[] plants = new String[]{
                "KG I",
                "KG II",
                "PreNursery",
                "Nursery"

        };
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, plants
        );


        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);
                // System.out.println(""+appTitle);
                // update toolbar title
                getSupportActionBar().setTitle(appTitle);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });
        // Save / update the user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeworkString = input.getText().toString();
                dateString = et_date.getText().toString();
                Homework homework = new Homework(txtClass.getText().toString().trim(), homeworkString, dateString);
                addHomework(homework);
                Toast.makeText(UpdateHomeworkActivity.this, "Homework uploded successfully", Toast.LENGTH_SHORT).show();
                // Check for already existed userId
//                if (TextUtils.isEmpty(userId)) {
//                    addHomework(homeworkString);
//                } else {
//                    updateHomework(homeworkString);
//                }
            }
        });
        toggleButton();
        mFirebaseHWDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                maxId = dataSnapshot.getChildrenCount() + 1;
                System.out.println("maxId=" + maxId);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mFirebaseHWDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(UpdateHomeworkActivity.this, "Homework uploded successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateHomeworkActivity.this, "Error uploding homework, please try again", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Changing btn_menu_sansthan text
    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            btnSave.setText("Save");
        } else {
            btnSave.setText("Updated");
        }
    }

    /**
     * Creating new user node under 'users'
     */
    private void addHomework(Homework homework) {


//        if (TextUtils.isEmpty(userId)) {
//            userId = mFirebaseDatabase.push().getKey();
//        }
//
//        User user = new User(homework);

        mFirebaseHWDatabase.child(String.valueOf(maxId)).setValue(homework);

        //addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.homework);

                // Display newly updated name and email


                // clear edit text
                input.setText("");


                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateHomework(String homework) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(homework))
            mFirebaseDatabase.child(userId).child("homework").setValue(homework);
    }


}




