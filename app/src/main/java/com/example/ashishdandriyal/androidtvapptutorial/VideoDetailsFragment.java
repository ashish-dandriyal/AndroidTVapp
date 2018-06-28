package com.example.ashishdandriyal.androidtvapptutorial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v17.leanback.app.DetailsSupportFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;
import android.util.Log;
import android.widget.Toast;

import com.example.ashishdandriyal.androidtvapptutorial.commons.Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URISyntaxException;

public class VideoDetailsFragment extends DetailsSupportFragment {
    private static final String TAG = VideoDetailsFragment.class.getSimpleName();

    private static final int DETAIL_THUMB_WIDTH = 274;
    private static final int DETAIL_THUMB_HEIGHT = 274;

    private static final String MOVIE = "Movie";

    private CustomFullWidthDetailsOverviewRowPresenter mFwdorPresenter;
    private SimpleBackgroundManager mSimpleBackgroundManager;

    private Movie mSelectedMovie;
    private DetailsRowBuilderTask mDetailsRowBuilderTask;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mFwdorPresenter = new CustomFullWidthDetailsOverviewRowPresenter(new DetailsDescriptionPresenter());
        mSimpleBackgroundManager = new SimpleBackgroundManager(getActivity());
        mSelectedMovie = (Movie)getActivity().getIntent().getParcelableExtra(MOVIE);
        Log.i(TAG, "VideoDetailsFragment onClick() called ["+mSelectedMovie+"]");
        mDetailsRowBuilderTask = (DetailsRowBuilderTask) new DetailsRowBuilderTask().execute(mSelectedMovie);
        try {
            mSimpleBackgroundManager.updateBackgroundByURL(mSelectedMovie.getCardImageUrl());
        } catch (URISyntaxException e){
            Log.i(TAG, "Exception occured "+e);
        }
    }

    @Override
    public void onStop() {
        mDetailsRowBuilderTask.cancel(true);
        super.onStop();
    }

    private class DetailsRowBuilderTask extends AsyncTask<Movie, Integer, DetailsOverviewRow> {

        @Override
        protected DetailsOverviewRow doInBackground(Movie... params) {
            DetailsOverviewRow row = new DetailsOverviewRow(mSelectedMovie);
            try {
                Bitmap poster = Picasso.with(getActivity())
                        .load(mSelectedMovie.getCardImageUrl())
                        .resize(Utils.convertDpToPixel(getActivity().getApplicationContext(), DETAIL_THUMB_WIDTH),
                                Utils.convertDpToPixel(getActivity().getApplicationContext(), DETAIL_THUMB_HEIGHT))
                        .centerCrop()
                        .get();
                row.setImageBitmap(getActivity(), poster);
            } catch (IOException e) {
                Log.i(TAG, e.toString());
            }
            return row;
        }

        @Override
        protected void onPostExecute(DetailsOverviewRow row){
            Log.i(TAG, "VideoDetailsFragment onPostExecute()");

            SparseArrayObjectAdapter sparseArrayObjectAdapter = new SparseArrayObjectAdapter();
            for(int i =0; i<3; i++){
                if(i == 0) sparseArrayObjectAdapter.set(i, new Action(i, "WATCH", "Free!"));
                else if (i == 1) sparseArrayObjectAdapter.set(i, new Action(i, "Trailer", "$0.00"));
                else if (i == 2) sparseArrayObjectAdapter.set(i, new Action(i, "Download", "$9.99"));
            }
            row.setActionsAdapter(sparseArrayObjectAdapter);

            mFwdorPresenter.setOnActionClickedListener(new OnActionClickedListener() {
                @Override
                public void onActionClicked(Action action) {
                    if(action.getId() == 0){
                        Log.i(TAG, "onActionClicked() "+action.getId());
                        Intent intent = new Intent(getActivity(), PlayerActivity.class);
                        intent.putExtra("Movie", mSelectedMovie);
                        intent.putExtra("shouldStart", true);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "For Now, Watch It for FREE!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            // These could be operations that can be performed for a video.
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());
            for(int i=0; i<10; i++){
                Movie movie = new Movie();
                movie.setCardImageUrl("https://vignette.wikia.nocookie.net/spiderman/images/e/ea/Spidey_1.png/revision/latest?cb=20140816173914");
                movie.setTitle("Title_"+i);
                movie.setStudio("Studio_"+i);
                listRowAdapter.add(movie);
            }
            HeaderItem headerItem = new HeaderItem(0, "Related Videos");
            ClassPresenterSelector classPresenterSelector = new ClassPresenterSelector();
            mFwdorPresenter.setInitialState(FullWidthDetailsOverviewRowPresenter.STATE_SMALL);

            classPresenterSelector.addClassPresenter(DetailsOverviewRow.class, mFwdorPresenter);
            classPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());

            ArrayObjectAdapter adapter = new ArrayObjectAdapter(classPresenterSelector);
            adapter.add(row);
            adapter.add(new ListRow(headerItem, listRowAdapter));
            setAdapter(adapter);
        }

    }
}
