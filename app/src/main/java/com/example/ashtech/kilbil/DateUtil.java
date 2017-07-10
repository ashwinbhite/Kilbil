package com.example.ashtech.kilbil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rahul on 7/10/2017.
 */

public class DateUtil {

    public static Date stringToDate(String sdt) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        try {
            date = simpleDateFormat.parse(sdt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
}
