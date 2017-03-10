package com.example.andydesk.popularmovies;

import android.content.Context;

import com.example.andydesk.popularmovies.Utilities.FetchMoviesTask;
import com.example.andydesk.popularmovies.Utilities.FetchTrailerTask;

import java.io.Serializable;

/**
 * Created by owner on 05/12/2016.
 */
public class MovieObject implements Serializable {
    private String genre;
    private String title;
    private String releaseDate;
    private double rating;
    private String plot;
    private String imageUrl;
    private int id;
    private Context context;

    public MovieObject(String title, String releaseDate, String genre, int id){
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.title = title;
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public String getPlot() {
        if(plot == null) {
            return "n/a";
        }

        else {
            return plot;
        }
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setImage(String url) {
        this.imageUrl = url;
    }

    public String getImage() {
        return imageUrl;
    }

    /**
     * Retruns the base URL  of the trailer database for this movie object
     * @return trailer database base URL
     */
    public String getTrailerDatabase() {
        String trailerDatabaseUrl = "";
        trailerDatabaseUrl = context.getString(R.string.base_url) + String.valueOf(id) + "/videos?";
        return trailerDatabaseUrl;
    }
}
