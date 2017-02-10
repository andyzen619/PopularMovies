package com.example.andydesk.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.andydesk.popularmovies.Fragments.MovieGridFragment;

//TODO: Change share preferences sort order setting when checked
//TODO: Implement refresh

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new MovieGridFragment()) // when adding fragment to
                    // activity, use Fragment manager to add id of activity xml and fragment
                    // and commit.
                    .commit();
        }

        //TODO: Add Sort order setting to share preferences (1st)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pref_sort_by_rating", "false");
        editor.commit();

    }
}
