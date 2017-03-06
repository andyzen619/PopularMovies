package com.example.andydesk.popularmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.andydesk.popularmovies.Utilities.FetchMoviesTask;
import com.example.andydesk.popularmovies.Utilities.FetchTrailerTask;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class MovieDetailActivty extends AppCompatActivity {
    private MovieObject movieObject;
    private String youtubeTrailerUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_activty);
        movieObject = getMovieObject(savedInstanceState);
        movieObject.setContext(getApplicationContext());
        youtubeTrailerUrl = getYoutubeTrailerUrl();
    }

    /**
     * Obtains the movie object of the movie associated with the array adapter item.
     * @param savedInstanceState
     * @return
     */
    private MovieObject getMovieObject(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
               return movieObject = (MovieObject) extras.get("movieObject");
            }
        }
        else {
            return movieObject = (MovieObject) savedInstanceState.getSerializable("movieObject");
        }
        return null;
    }

    /**
     * Retreive that youtube url of the trailer of chosen movie;
     * @return url string of youtube url
     */
    private String getYoutubeTrailerUrl() {
        String resultUrl = null;
        FetchTrailerTask movieTrailerUrl = new FetchTrailerTask();
        movieTrailerUrl.setContext(getApplicationContext());
        try {
            movieTrailerUrl.execute(movieObject.getTrailerDatabase());
            resultUrl = movieTrailerUrl.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return resultUrl;
    }
}
