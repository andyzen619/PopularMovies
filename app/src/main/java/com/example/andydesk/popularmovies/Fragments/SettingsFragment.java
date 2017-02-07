package com.example.andydesk.popularmovies.Fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.andydesk.popularmovies.R;

/**
 * Created by owner on 07/02/2017.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
