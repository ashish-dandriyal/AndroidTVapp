package com.example.ashishdandriyal.androidtvapptutorial.rest;

import android.util.Log;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceAccess {

    public static final String TAG = ServiceAccess.class.getSimpleName();

    public static String creatingURLConnection (String GET_URL) {
        String response = "";
        HttpURLConnection conn ;
        StringBuilder jsonResults = new StringBuilder();
        try {
            //setting URL to connect with
            URL url = new URL(GET_URL);
            //creating connection
            conn = (HttpURLConnection) url.openConnection();
           /*
           converting response into String
           */
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            int read;
            char[] buff = new char[2048];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
            response = jsonResults.toString();
            Log.i(TAG, response);
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
