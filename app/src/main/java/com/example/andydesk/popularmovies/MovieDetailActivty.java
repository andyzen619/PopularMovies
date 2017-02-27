package com.example.andydesk.popularmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivty extends AppCompatActivity {
    private MovieObject movieObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_activty);
        movieObject = getMovieObject(savedInstanceState);

        //ImageView detailMovieImageView = (ImageView) findViewById(R.id.movieDetailImageView);
        //String url = getString(R.string.base_url) + "w780" + movieObject.getImage();
        //Picasso.with(getApplicationContext()).load(url).into(detailMovieImageView);

        VideoView videoView = (VideoView) findViewById(R.id.movieTrailerView);
        videoView.


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
}
