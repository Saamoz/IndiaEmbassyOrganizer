package com.india.saamoz.embassyorganizer;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by Gaming PC on 4/4/2017.
 */

public class DatePickerFragment extends DialogFragment {


    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        int oldYear = this.getArguments().getInt("YEAR");
        int oldMonth = this.getArguments().getInt("MONTH");
        int oldDay = this.getArguments().getInt("DAY");

        if (oldYear == 0){
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }else{
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), oldYear, oldMonth, oldDay);
        }
    }

}
