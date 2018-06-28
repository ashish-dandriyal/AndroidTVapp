package com.example.ashishdandriyal.androidtvapptutorial;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v17.leanback.app.BrowseSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ashishdandriyal.androidtvapptutorial.commons.Utils;
import com.example.ashishdandriyal.androidtvapptutorial.rest.DataWrapper;
import com.example.ashishdandriyal.androidtvapptutorial.rest.ServiceAccess;
import com.example.ashishdandriyal.androidtvapptutorial.search.SearchActivity;

import java.util.List;

public class BlankFragment extends BrowseSupportFragment {
    private static final String TAG = BlankFragment.class.getSimpleName();

    private static final int GRID_ITEM_WIDTH = 300;
    private static final int GRID_ITEM_HEIGHT = 200;

    private ArrayObjectAdapter mRowsAdapter;
    private static SimpleBackgroundManager simpleBackgroundManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        simpleBackgroundManager = new SimpleBackgroundManager(getActivity());

        setupUIElements();
        loadRows();
        setupEventListeners();
    }

    private void setupUIElements(){
        Drawable myIcon = getResources().getDrawable( R.drawable.irdeto );
        setBadgeDrawable(myIcon);
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setBrandColor(getResources().getColor(R.color.fastlane_background));
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private class GridItemPresenter extends Presenter {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent){
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item){
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder){

        }
    }

    private void loadRows(){
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        List<DataWrapper.Live> live = null;
        List<DataWrapper.Vod> vod = null;
        List<DataWrapper.Epg> epg = null;

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String liveResponse = ServiceAccess.creatingURLConnection("https://indiaps.irdeto.com/api/live");
            live = (List<DataWrapper.Live>) DataWrapper.fromJson(liveResponse, DataWrapper.Live.class);
            Log.i(TAG, live.toString());

            String vodResponse = ServiceAccess.creatingURLConnection("https://indiaps.irdeto.com/api/vod");
            vod = (List<DataWrapper.Vod>) DataWrapper.fromJson(vodResponse, DataWrapper.Vod.class);
            Log.i(TAG, vod.toString());

            String epgResponse = ServiceAccess.creatingURLConnection("https://indiaps.irdeto.com/api/epg");
            epg = (List<DataWrapper.Epg>) DataWrapper.fromJson(epgResponse, DataWrapper.Epg.class);
            Log.i(TAG, epg.toString());
        }

        List<Movie> movie = null;
        /* CardPresenter */
        for(int j=1; j<=3; j++){
            String headerName = "No Name";
            if (j==1) {
                headerName = "Live";
                movie = Utils.getMovieByData(live);
            } else if (j==2) {
                headerName = "Vod";
                movie = Utils.getMovieByVod(vod);
            } else if (j==3) {
                headerName = "Epg";
                movie = Utils.getMovieByEpg(epg);
            }

            HeaderItem cardPresenterHeader = new HeaderItem(1, headerName);
            CardPresenter cardPresenter = new CardPresenter();
            ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(cardPresenter);

            for (Movie m : movie)
                cardRowAdapter.add(m);

            mRowsAdapter.add(new ListRow(cardPresenterHeader, cardRowAdapter));
        }

        /* GridPresenter */
        HeaderItem gridItemPresenterHeader = new HeaderItem(0, "Information");
        GridItemPresenter  mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        gridRowAdapter.add("Help");
        gridRowAdapter.add("Settings");
        mRowsAdapter.add(new ListRow(gridItemPresenterHeader, gridRowAdapter));

        setAdapter(mRowsAdapter);
    }

    private void setupEventListeners() {
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
        setOnItemViewClickedListener(new ItemViewClickedListener());

        setOnSearchClickedListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            // each time the item is clicked, code inside here will be executed.
            Log.i(TAG, "Some item has been clicked!");
            if(item instanceof Movie){
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.MOVIE, (Movie) item);
                getActivity().startActivity(intent);
            } else if(item instanceof String){
                if(item == "Help"){
                    Intent intent = new Intent(getActivity(), Help.class);
                    startActivity(intent);
                }
            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {

        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row){
            Log.i(TAG, "Some item has been selected!");
            // each time the item is selected, code inside here will be executed.
            if (item instanceof String) { // GridItemPresenter row
                simpleBackgroundManager.clearBackground();
            } else if (item instanceof Movie) { // CardPresenter row
                simpleBackgroundManager.updateBackground(getActivity().getDrawable(R.drawable.movie));
            }
        }
    }
}
