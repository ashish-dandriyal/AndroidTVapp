package com.example.ashishdandriyal.androidtvapptutorial.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.ashishdandriyal.androidtvapptutorial.R;

public class SearchActivity extends FragmentActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //searchFragment = (SearchFragment) getFragmentManager().findFragmentById(R.id.seach_fragment);
    }

    /*@Override
    public boolean onSearchRequested(){
        startActivity(new Intent(this, SearchActivity.class));
        return true;
    }*/

}
