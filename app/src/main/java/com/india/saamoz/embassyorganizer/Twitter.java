package com.india.saamoz.embassyorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Twitter extends AppCompatActivity {

    private static final String baseURl = "https://twitter.com";

    private static final String widgetInfo = "<a class=\"twitter-timeline\" href=\"https://twitter.com/IndianEmbRiyadh\">Tweets by IndianEmbRiyadh</a> <script async src=\"//platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        WebView twitter = (WebView) findViewById(R.id.twitter);
        twitter.getSettings().setDomStorageEnabled(true);
        twitter.getSettings().setJavaScriptEnabled(true);
        twitter.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);
    }
}
