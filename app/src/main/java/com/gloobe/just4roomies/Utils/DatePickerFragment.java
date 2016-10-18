package com.gloobe.just4roomies.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.gloobe.just4roomies.R;

import java.util.Calendar;

/**
 * Created by rudielavilaperaza on 27/08/16.
 */
public class DatePickerFragment extends android.support.v4.app.DialogFragment implements DatePickerDialog.OnDateSetListener {


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        return datePickerDialog;

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        ((Button) getActivity().findViewById(R.id.btOfrecer_Fecha)).setText(day + "/" + month + "/" + year);
    }
}
