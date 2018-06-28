package com.example.ashishdandriyal.androidtvapptutorial;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class DetailsActivity extends FragmentActivity {

    public static final String MOVIE = "Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
