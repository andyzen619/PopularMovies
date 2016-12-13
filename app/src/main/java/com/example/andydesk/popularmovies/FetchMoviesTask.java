package com.example.andydesk.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andy DESK on 12/6/2016.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<MovieObject>> {

    private ArrayList<MovieObject> movieObjectArrayList;
    private String movieUrl;
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    public FetchMoviesTask() {
    }

    @Override
    protected ArrayList<MovieObject> doInBackground(String... params) {
        if(params.length == 0) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String rawMovieString;

        String baseUrl = "http://api.themoviedb.org/3/movie/popular?";
        Uri urlUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter("api_key=", BuildConfig.OPEN_MOVIE_API_KEY)
                .build();
        try {
            URL url = new URL(urlUri.toString());
            Log.v(LOG_TAG, "Built url: " + urlUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection(); // set reference too url connection by opening the the url we have created
            urlConnection.setRequestMethod("GET");// set the request method so that we can fetch JSON data;
            urlConnection.connect();// start the connection

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null) {
                
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieObjectArrayList;
    }
}
