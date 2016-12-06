package com.example.andydesk.popularmovies;

/**
 * Created by owner on 05/12/2016.
 */
public class MovieObject{
    private String genre;
    private String title;
    private String releaseDate;
    private String rating;
    private String plot;
    private String imageUrl;

    public MovieObject(String title, String releaseDate, String Genre){
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.title = title;
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

    public String getPlot() {
        if(plot == null) {
            return "n/a";
        }

        else {
            return plot;
        }
    }

    public void setImage(String url) {
        this.imageUrl = url;
    }

    public String getImage() {
        return imageUrl;
    }


}
