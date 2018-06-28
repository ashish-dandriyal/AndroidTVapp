package com.example.ashishdandriyal.androidtvapptutorial.commons;

import android.annotation.TargetApi;
import android.arch.core.util.Function;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ashishdandriyal.androidtvapptutorial.Movie;
import com.example.ashishdandriyal.androidtvapptutorial.rest.DataWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    /*
     * Making sure public utility methods remain static
     */
    private Utils() {
    }

    /**
     * Returns the screen/display size
     */
    public static Point getDisplaySize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Shows a (long) toast
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Shows a (long) toast.
     */
    public static void showToast(Context context, int resourceId) {
        Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_LONG).show();
    }

    public static int convertDpToPixel(Context ctx, int dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    /**
     * Formats time in milliseconds to hh:mm:ss string format.
     */
    public static String formatMillis(int millis) {
        String result = "";
        int hr = millis / 3600000;
        millis %= 3600000;
        int min = millis / 60000;
        millis %= 60000;
        int sec = millis / 1000;
        if (hr > 0) {
            result += hr + ":";
        }
        if (min >= 0) {
            if (min > 9) {
                result += min + ":";
            } else {
                result += "0" + min + ":";
            }
        }
        if (sec > 9) {
            result += sec;
        } else {
            result += "0" + sec;
        }
        return result;
    }

    public static long getDuration(String videoUrl) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mmr.setDataSource(videoUrl, new HashMap<String, String>());
        } else {
            mmr.setDataSource(videoUrl);
        }
        return Long.parseLong(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
    }

    @TargetApi(24)
    public static List<Movie> getMovieByData(List<DataWrapper.Live> live) {

        List<Movie> movieList = live.stream().map(db -> {
            Movie movie = new Movie(db);
            return movie;
        }).collect(Collectors.toList());

        return movieList;
    }

    @TargetApi(24)
    public static List<Movie> getMovieByVod(List<DataWrapper.Vod> vod) {

        List<Movie> movieList = vod.stream().map(db -> {
            Movie movie = new Movie(db);
            return movie;
        }).collect(Collectors.toList());

        return movieList;
    }

    @TargetApi(24)
    public static List<Movie> getMovieByEpg(List<DataWrapper.Epg> epg) {

        List<Movie> movieList = epg.stream().map(db -> {
            Movie movie = new Movie(db);
            return movie;
        }).collect(Collectors.toList());

        return movieList;
    }

}
