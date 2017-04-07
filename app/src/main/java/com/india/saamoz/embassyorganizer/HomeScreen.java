package com.india.saamoz.embassyorganizer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void open_about_us(View view){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    public void open_contact(View view){
        Intent intent = new Intent(this, Contact.class);
        startActivity(intent);
    }

    public void open_complaints(View view){
        Intent intent = new Intent(this, Complaint.class);
        startActivity(intent);
    }

    public void open_website(View view){
        String url = "http://www.indianembassy.org.sa";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void open_twitter(View view){
        Intent intent = new Intent(this, Twitter.class);
        startActivity(intent);
    }
}
