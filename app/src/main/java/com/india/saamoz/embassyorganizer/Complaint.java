package com.india.saamoz.embassyorganizer;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import java.util.Calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Complaint extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    final static String TAG = "SAAMOZINDIA";

    EditText fName, sName, pNum, fatherName, motherName, iquamaNum, indiaAddress, emailAddress, compName, passNum, info;
    Spinner gender;
    Button chooseDate;

    ArrayList<EditText> required = new ArrayList<EditText>();

    public int yearBirth;
    int monthBirth;
    int dayBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        fName = (EditText) findViewById(R.id.fName);
        sName = (EditText) findViewById(R.id.sName);
        pNum = (EditText) findViewById(R.id.phoneNum);
        fatherName = (EditText) findViewById(R.id.fatherName);
        motherName = (EditText) findViewById(R.id.motherName);
        iquamaNum = (EditText) findViewById(R.id.iquamaNum);
        indiaAddress = (EditText) findViewById(R.id.indiaAddress);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        compName = (EditText) findViewById(R.id.compName);
        passNum = (EditText) findViewById(R.id.passNum);
        info = (EditText) findViewById(R.id.info);

        gender = (Spinner) findViewById(R.id.spinner);

        chooseDate = (Button) findViewById(R.id.DoB);

        required.addAll(Arrays.asList(fName, sName, pNum, fatherName, motherName, indiaAddress));
    }

    public void complain(View view){
        boolean isFilled = checkRequiredInputs();
        boolean validAge = checkDateOfBirth(yearBirth);
        if(isFilled && validAge){
            String subject = "Embassy Request by: " + fName.getText() + " " + sName.getText();
            String body = composeBody();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("message/rfc822");
            email.putExtra(Intent.EXTRA_EMAIL, new String[] {"madadsaudiarabia@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(email);
        }else if (validAge){
            Toast.makeText(this, "Please Fill Out All Required Fields", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please Enter Valid Date of Birth", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkDateOfBirth(int year) {
        Calendar c = Calendar.getInstance();
        int thisYear = c.get(Calendar.YEAR);

        return (year < thisYear - 8);
    }

    private String composeBody() {
        String body = "";
        body += "First Name is: " + fName.getText() + "\n";
        body += "Last Name is: " + sName.getText() + "\n";
        body += "Phone Number is: " + pNum.getText() + "\n";
        body += "Gender is: " + gender.getSelectedItem().toString() + "\n";
        body += "Father's Name is: " + fatherName.getText() + "\n";
        body += "Mother's Name is: " + motherName.getText() + "\n";
        body += "Iquama Number is: " + iquamaNum.getText() + "\n";
        body += "Address is: " + indiaAddress.getText() + "\n";
        body += "Email Address: " + emailAddress.getText() + "\n";
        body += "Company Name is: " + compName.getText() + "\n";
        body += "Passport Number is: " + compName.getText() + "\n";
        body += "Any Additional Indo: " + info.getText() + "\n\n\n";

        body += "Composed by India in Saudi Arabia Message Algorithm";

        return body;
    }

    private boolean checkRequiredInputs() {
       Log.v(TAG, "Array of required contains:" + required.toString());

       for (EditText text : required){
           Log.d(TAG , "Is " + text.getHint() + " filled :" + Boolean.toString(text.getText().toString().equals("")));

            if (text.getText().toString().equals("")){
                return false;
            }
        }

        if (gender.getSelectedItem().toString().equals(getResources().getStringArray(R.array.genderChoice)[0])){
            return false;
        }

        Calendar c = Calendar.getInstance();
        int thisYear = c.get(Calendar.YEAR);

        if (yearBirth == 0){
            return false;
        }

        return true;
    }

    public void chooseDoB(View v){
        Bundle dateBundle = new Bundle();
        dateBundle.putInt("YEAR", yearBirth);
        dateBundle.putInt("MONTH", monthBirth);
        dateBundle.putInt("DAY", dayBirth);

        DialogFragment calFragment = new DatePickerFragment();

        calFragment.setArguments(dateBundle);
        calFragment.show(getFragmentManager(), "datepicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "Date recorded is: " + Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth));

        yearBirth = year;
        monthBirth = month;
        dayBirth = dayOfMonth;

        chooseDate.setText("Date of Birth is:  " + yearBirth + "/" + monthBirth + "/" + dayBirth);
    }
}
