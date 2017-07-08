package com.example.ashtech.kilbil;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class TeacherLoginActivity extends AppCompatActivity {


    private Button btnUpload;
    private Button btnFetch;
    private Button btnLogin;
    private EditText edtUsername;
    private EditText edtPassword;
    private LinearLayout llTeacherlogin;
    private LinearLayout lluploadButtons;
    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPreferences;
    private String userEmail = "";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        llTeacherlogin = (LinearLayout) findViewById(R.id.ll_teacher_login);
        lluploadButtons = (LinearLayout) findViewById(R.id.ll_upload_btns);
        btnLogin = (Button) findViewById(R.id.btn_teacher_login);
        edtUsername = (EditText) findViewById(R.id.edt_login_user);
        edtPassword = (EditText) findViewById(R.id.edt_login_pass);
        firebaseAuth = FirebaseAuth.getInstance();

        setEditTextValues(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                checklogin(TeacherLoginActivity.this);
            }
        });


        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnFetch = (Button) findViewById(R.id.fetch);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {

                Intent myIntent = new Intent(TeacherLoginActivity.this, UploadActivity.class);
                myIntent.putExtra("userEmail", userEmail);
                TeacherLoginActivity.this.startActivity(myIntent);

            }

        });
        btnFetch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {

                Intent myIntent1 = new Intent(TeacherLoginActivity.this, UpdateHomeworkActivity.class);
                myIntent1.putExtra("userEmail", userEmail);
                TeacherLoginActivity.this.startActivity(myIntent1);

            }

        });

    }

    private void setEditTextValues(TeacherLoginActivity teacherLoginActivity) {
        edtUsername.setText(sharedPreferences.getString("username", ""));
        edtPassword.setText(sharedPreferences.getString("password", ""));
    }


    private void checklogin(final Context context) {

        if (isValid()) {
            firebaseAuth.signInWithEmailAndPassword(edtUsername.getText().toString().trim(),
                    edtPassword.getText().toString().trim()).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(context, "Fail to login please check you username & password", Toast.LENGTH_LONG).show();

                }
            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    userEmail = authResult.getUser().getEmail();
                    Toast.makeText(context, "Successful.", Toast.LENGTH_LONG).show();
                    lluploadButtons.setVisibility(View.VISIBLE);
                    llTeacherlogin.setVisibility(View.GONE);

                    saveUserPassword();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(context, "Please enter Username & password", Toast.LENGTH_LONG).show();
        }


    }

    private void saveUserPassword() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", edtUsername.getText().toString());
        editor.putString("password", edtPassword.getText().toString());
        editor.commit();
    }

    private boolean isValid() {
        if (edtUsername.getText().toString().trim().length() > 0 &&
                edtPassword.getText().toString().trim().length() > 0) {

            return true;
        } else {
            return false;
        }
    }


}
