package com.example.andydesk.popularmovies.Adapters;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andydesk.popularmovies.MovieObject;
import com.example.andydesk.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by owner on 05/12/2016.
 */

public class MyMovieAdapter extends ArrayAdapter {
    ArrayList<MovieObject> movieObjects;

    public MyMovieAdapter(Context context, int resource,  ArrayList<MovieObject> movieObjects) {
        super(context, resource, movieObjects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieObject movie = (MovieObject) getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_movie_item, parent, false);
        }

        ImageView movie_image_view = (ImageView) convertView.findViewById(R.id.movie_item_image_view);
        TextView genreTextView = (TextView) convertView.findViewById(R.id.genre_text_view);
        TextView releaseDateTextView = (TextView) convertView.findViewById(R.id.year_text_view);

        //Populate the each grid item with picture, year, and genre of movie
        Picasso.with(getContext()).load(movie.getImage()).into(movie_image_view);
        genreTextView.setText(movie.getGenre());
        releaseDateTextView.setText(movie.getReleaseDate());

        return super.getView(position, convertView, parent);
    }
}
