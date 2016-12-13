package com.example.andydesk.popularmovies.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andydesk.popularmovies.Adapters.MyMovieAdapter;
import com.example.andydesk.popularmovies.FetchMoviesTask;
import com.example.andydesk.popularmovies.MovieObject;
import com.example.andydesk.popularmovies.R;

import java.util.ArrayList;

public class MovieGridFragment extends Fragment {

    private FetchMoviesTask fetchMoviesTask;
    private MyMovieAdapter movieAdapter;
    private AsyncTask<String, Void, ArrayList<MovieObject>> movieObjectArrayList;

    public MovieGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchMoviesTask = new FetchMoviesTask();
        movieObjectArrayList = fetchMoviesTask.execute();
        movieAdapter.addAll(movieObjectArrayList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movie_grid, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }
}
