package com.example.andydesk.popularmovies.Utilities;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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

public class FetchMoviesTask extends AsyncTask<SharedPreferences, Void, ArrayList<MovieObject>> {

    private ArrayList<MovieObject> movieObjectArrayList = new ArrayList<MovieObject>();
    private String movieUrl;
    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();
    private String basePopularUrl = "http://api.themoviedb.org/3/movie/popular?";
    private String baseRatingUrl = "http://api.themoviedb.org/3/movie/top_rated?";
    private Boolean isSortByRating;
    private Uri urlUri;

    @Override
    protected ArrayList<MovieObject> doInBackground(SharedPreferences... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String rawMovieString = null;
        isSortByRating = params[0].getBoolean("preference_sort_by_rating", false);

        if (isSortByRating) {
            urlUri = Uri.parse(baseRatingUrl).buildUpon()
                    .appendQueryParameter("api_key", BuildConfig.OPEN_MOVIE_API_KEY)
                    .build();
        }
        else {
            urlUri = Uri.parse(basePopularUrl).buildUpon()
                    .appendQueryParameter("api_key", BuildConfig.OPEN_MOVIE_API_KEY)
                    .build();
        }
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
            while((line = bufferedReader.readLine()) != null){
                buffer.append(line + "\n");
            }


            rawMovieString = buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        movieObjectArrayList = getMovieList(rawMovieString);
        return movieObjectArrayList;

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
                movieObject = new MovieObject(title, releaseDate, "genre", id);
                movieObject.setImage(imagePath);
                movieObjectArrayList.add(movieObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieObjectArrayList;
    }

}
