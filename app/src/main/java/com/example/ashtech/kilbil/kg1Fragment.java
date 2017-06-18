package com.example.ashtech.kilbil;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class kg1Fragment extends android.app.Fragment {


    public kg1Fragment() {
        // Required empty public constructor
    }
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseHWDatabase;


    private ListView lv_homework;// ListView
    private ProgressDialog progressDialog;
    private HomeworkAdapter hwAdapter;
    private List<Homework> homeworkList = new ArrayList<>();
    private Context mContext2;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);
        mContext2=this.getActivity();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseHWDatabase = mFirebaseInstance.getReference("Homework");
        lv_homework= (ListView) view.findViewById(R.id.lv_hw);
        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait ...");
        progressDialog.show();



        Query query = mFirebaseHWDatabase.child("kg1");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("query onDataChange");
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        System.out.println(ds.getValue().toString());
                        Homework homework = ds.getValue(Homework.class);
                        homeworkList.add(homework);

                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    hwAdapter = new HomeworkAdapter(homeworkList,mContext2);
                    lv_homework.setAdapter(hwAdapter);
                }else{
                    Toast.makeText(mContext2, "No records found", Toast.LENGTH_LONG).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mFirebaseHWDatabase.addValueEventListener(valueEventListener);
        return view;
    }

}
