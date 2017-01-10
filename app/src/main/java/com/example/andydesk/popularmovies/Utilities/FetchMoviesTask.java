package com.example.andydesk.popularmovies.Utilities;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.andydesk.popularmovies.BuildConfig;
import com.example.andydesk.popularmovies.MovieObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andy DESK on 12/6/2016.
 */

public class FetchMoviesTask extends AsyncTask<Void, Void, ArrayList<MovieObject>> {

    private ArrayList<MovieObject> movieObjectArrayList;
    private String movieUrl;
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    @Override
    protected ArrayList<MovieObject> doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String rawMovieString = null;

        String baseUrl = "http://api.themoviedb.org/3/movie/popular?";
        Uri urlUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter("api_key", BuildConfig.OPEN_MOVIE_API_KEY)
                .build();
        try {
            //Assign built url
            URL url = new URL(urlUri.toString());
            Log.v(LOG_TAG, "Built url: " + urlUri.toString());

            //Start connection with selected request method.
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null) {
                rawMovieString = null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) { //TODO: line never reaches null (BUG)
                buffer.append(line + "\n");
            }


            rawMovieString = buffer.toString();

            //TODO: Extract info from raw movie string into movie objects.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getMovieList(rawMovieString);

    }

    public ArrayList<MovieObject> getMovieList(String jsonString) {

        JSONObject movie;
        MovieObject movieObject;

        try {
            JSONObject movieJsonObject = new JSONObject(jsonString);
            JSONArray results = movieJsonObject.getJSONArray("results");
            for(int i = 0; i < results.length(); i++) {
                movie = (JSONObject) results.get(i);
                String releaseDate =  movie.getString("release_date");
                JSONArray genre  = movie.getJSONArray("genre_ids");
                String imagePath = movie.getString("poster_path");
                String title = movie.getString("title");
                int id = movie.getInt("id");
                movieObjectArrayList.add(new MovieObject(title, releaseDate, "genre", id)
                .getImage(imagePath));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieObjectArrayList;
    }

}
