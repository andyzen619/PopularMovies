package com.example.andydesk.popularmovies;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andydesk.popularmovies.Utilities.FetchTrailerTask;
import java.util.concurrent.ExecutionException;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.w3c.dom.Text;


public class MovieDetailActivty extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private MovieObject movieObject;
    private String youtubeTrailerUrl;
    public static final String youtubeApiKey = "AIzaSyBORBGAqLbU4CrsXrHHEHTw4bjx5Li6DKQ";
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_activty);

        movieObject = getMovieObject(savedInstanceState);
        movieObject.setContext(getApplicationContext());

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.movie_detail_youtube_trailer_view);
        youTubePlayerView.initialize(youtubeApiKey, this);

        TextView title = (TextView) findViewById(R.id.movie_detail_title_text_view);
        title.setText(movieObject.getTitle());
        TextView plot = (TextView) findViewById(R.id.movie_detail_plot_text_view);
        plot.setText(movieObject.getPlot());
        TextView rating = (TextView) findViewById(R.id.movie_detail_ratomg_view);
        rating.setText(Double.toString(movieObject.getRating()));
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
    private String getYoutubeTrailerKey() {
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


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer,
                                        boolean wasRestored) {
        if(!wasRestored) {
            youTubePlayer.cueVideo(getYoutubeTrailerKey());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        CharSequence text = "Video play back failed";
        Toast toast  = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
