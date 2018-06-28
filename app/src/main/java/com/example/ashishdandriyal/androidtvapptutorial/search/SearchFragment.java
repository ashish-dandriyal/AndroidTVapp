package com.example.ashishdandriyal.androidtvapptutorial.search;

import android.support.v17.leanback.app.SearchSupportFragment;
import android.support.v17.leanback.widget.ObjectAdapter;

public class SearchFragment extends android.support.v17.leanback.app.SearchFragment implements SearchSupportFragment.SearchResultProvider{
    @Override
    public ObjectAdapter getResultsAdapter() {
        return null;
    }

    @Override
    public boolean onQueryTextChange(String newQuery) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
