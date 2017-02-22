package com.example.andydesk.popularmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andydesk.popularmovies.MovieDetailActivty;
import com.example.andydesk.popularmovies.MovieObject;
import com.example.andydesk.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 05/12/2016.
 */

public class MyMovieAdapter extends ArrayAdapter {
    ArrayList<MovieObject> movieObjects;

    public MyMovieAdapter(Context context, int resource, int textViewResourceId, ArrayList<MovieObject> movieObjects) {
        super(context, resource, textViewResourceId, (List) movieObjects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MovieObject movie = (MovieObject) getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_movie_item, parent, false);
        }

        ImageView movie_image_view = (ImageView) convertView.findViewById(R.id.movie_item_image_view);
        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.description_text_view);

        //Populate the each grid item with picture, year, and genre of movie
        String imageUrl = movie.getImage();
        Picasso.with(getContext()).load(imageUrl).into(movie_image_view);
        String description = movie.getTitle() + "\n" + movie.getReleaseDate();
        descriptionTextView.setText(description);

        movie_image_view.setOnClickListener(new View.OnClickListener() {
<<<<<<< HEAD
            @Override
            public void onClick(View v) {
=======

            @Override
            public void onClick(View v) {
                Intent movieDetailsIntent = new Intent(getContext(), MovieDetailActivty.class);
                movieDetailsIntent.putExtra("movieObejct", (MovieObject) getItem(position));
                getContext().startActivity(movieDetailsIntent);
            }
        });


>>>>>>> 46b76a8ff1c4cc449d7c300164cfe421cdaeb267

            }
        });
        return convertView;
    }


}
