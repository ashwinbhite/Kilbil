package com.example.ashtech.kilbil;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Rahul on 7/1/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    public OnDateSelectedListener dateselect;

    public interface OnDateSelectedListener {
        public void onDateSelected(String date);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dateselect = (OnDateSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateselect.onDateSelected(dayOfMonth + "/" + monthOfYear + "/" + year);
    }
}
