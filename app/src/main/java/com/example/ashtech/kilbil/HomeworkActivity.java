package com.example.ashtech.kilbil;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

//This class is for the homework page
public class HomeworkActivity extends AppCompatActivity{

    GridView grid;
    private LinearLayout homeworkLL;

    String[] web = {
            "Google",
            "Github",
            "Instagram",
            "Facebook"};
    int[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        grid=(GridView)findViewById(R.id.grid);
        homeworkLL= (LinearLayout) findViewById(R.id.homework_ll);
        CustomGrid adapter = new CustomGrid(HomeworkActivity.this, web, imageId);

        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
              // Toast.makeText(HomeworkActivity.this, "You Clicked at " +web[position], Toast.LENGTH_SHORT).show();

        switch (position){

                    case 0:
                        AboutFragment aboutFragment = new AboutFragment();
                        android.app.FragmentManager fm = getFragmentManager();
                        fm.beginTransaction().replace(R.id.activity_homework,aboutFragment,"AboutFragment").addToBackStack("AboutFragment").commit();
                        homeworkLL.setVisibility(View.GONE);//This is require else the fragment will get overlap on the existing view. try commenting this line and test.
                        break;
                    case 1:
                       NurseryFragment nurseryFragment=new NurseryFragment();
                        android.app.FragmentManager fm1 = getFragmentManager();
                        fm1.beginTransaction().replace(R.id.activity_homework,nurseryFragment,"NurseryFragment").addToBackStack("NurseryFragment").commit();
                        homeworkLL.setVisibility(View.GONE);
                        break;
                    case 2:
                        kg1Fragment kg1Fragment = new kg1Fragment();
                        android.app.FragmentManager fm2 = getFragmentManager();
                        fm2.beginTransaction().replace(R.id.activity_homework,kg1Fragment,"KG1Fragment").addToBackStack("kg1Fragment").commit();
                        homeworkLL.setVisibility(View.GONE);
                        break;
                    case 3:
                        kg2Fragment kg2Fragment = new kg2Fragment();
                        android.app.FragmentManager fm4 = getFragmentManager();
                        fm4.beginTransaction().replace(R.id.activity_homework,kg2Fragment,"KG2Fragment").addToBackStack("kg2Fragment").commit();
                        homeworkLL.setVisibility(View.GONE);
                        break;
                }

            }
        });
        

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        homeworkLL.setVisibility(View.VISIBLE);
    }
}
