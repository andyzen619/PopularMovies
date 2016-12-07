package com.example.andydesk.popularmovies;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Andy DESK on 12/6/2016.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<MovieObject>> {

    private ArrayList<MovieObject> movieObjectArrayList;
    private String movieUrl;

    public FetchMoviesTask(String url) {
        movieObjectArrayList = new ArrayList<MovieObject>();
        movieUrl = url;
    }

    @Override
    protected ArrayList<MovieObject> doInBackground(String... params) {
        if(params.length == 0) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String rawMovieString;

        try {

        }

        return movieObjectArrayList;
    }
}
