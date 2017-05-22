package com.example.ashtech.kilbil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateHomeworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_homework);

        // Get reference of widgets from XML layout
        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        // Initializing a String Array
        String[] plants = new String[]{
                "KG I",
                "KG II",
                "PreNursery",
                "Nursery"
        };

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,plants
        );

        /*
            setDropDownViewResource(int resource)
                Sets the layout resource to create the drop down views.

                Parameters : resource
                    the layout resource defining the drop down views
         */
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /*
            setAdapter(SpinnerAdapter adapter)
                Sets the Adapter used to provide the data which backs this Spinner.
         */
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
    }
    }



