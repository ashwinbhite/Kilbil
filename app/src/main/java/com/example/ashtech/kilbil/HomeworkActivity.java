package com.example.ashtech.kilbil;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

//This class is for the homework page
public class HomeworkActivity extends AppCompatActivity{

    GridView grid;

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
        CustomGrid adapter = new CustomGrid(HomeworkActivity.this, web, imageId);

        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
              // Toast.makeText(HomeworkActivity.this, "You Clicked at " +web[position], Toast.LENGTH_SHORT).show();

           switch (position){

                    case 0:
                        AboutFragment AboutFragment = new AboutFragment();
                        FragmentManager fm1 = getSupportFragmentManager();
                        fm1.beginTransaction().replace(R.id.activity_main, AboutFragment, "AboutFragment").commit();
                        break;
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }

            }
        });
        

    }
}
