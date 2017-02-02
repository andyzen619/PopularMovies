package com.example.andydesk.popularmovies.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.andydesk.popularmovies.Adapters.MyMovieAdapter;
import com.example.andydesk.popularmovies.SettingsActivty;
import com.example.andydesk.popularmovies.Utilities.FetchMoviesTask;
import com.example.andydesk.popularmovies.MovieObject;
import com.example.andydesk.popularmovies.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MovieGridFragment extends Fragment {

    private FetchMoviesTask fetchMoviesTask;
    private MyMovieAdapter movieAdapter;
    private ArrayList<MovieObject> movieObjectArrayList;

    public MovieGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_movie_grid, container, false);

        fetchMoviesTask = new FetchMoviesTask();
        fetchMoviesTask.execute();
        try {
            movieObjectArrayList = (ArrayList<MovieObject>) fetchMoviesTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        movieAdapter = new MyMovieAdapter(
                getActivity(),
                R.layout.grid_movie_item,
                R.id.description_text_view,
                movieObjectArrayList);

        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid_view);
        gridView.setAdapter(movieAdapter);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem settingsItem = (MenuItem) getActivity().findViewById(R.id.settingsButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refreshButton ) {
            refreshMovieList();
        }
        else if(item.getItemId() == R.id.settingsButton) {
            Intent settingsIntent = new Intent(getActivity(), SettingsActivty.class);
            startActivity(settingsIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Refresh the movie information from the host
     */
    private void refreshMovieList() {
        FetchMoviesTask refreshTask = new FetchMoviesTask();
        refreshTask.execute();
        try {
            movieObjectArrayList = refreshTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        movieAdapter.clear();
        movieAdapter.addAll(movieObjectArrayList);

        CharSequence text = "Updated";
        Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        toast.show();

    }
}
