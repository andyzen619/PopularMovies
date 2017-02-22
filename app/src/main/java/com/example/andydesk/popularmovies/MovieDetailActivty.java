package com.example.andydesk.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MovieDetailActivty extends AppCompatActivity {
    private MovieObject movieObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_activty);

        ImageView movieDetailImageView = (ImageView) findViewById(R.id.movieDetailImageView);
        Intent intent = getIntent();

    }
}
