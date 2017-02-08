package com.example.andydesk.popularmovies.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.andydesk.popularmovies.R;

/**
 * Created by owner on 07/02/2017.
 */

public class SettingsFragment extends PreferenceFragment {

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        String sortSetting = sharedPreferences.getString()
    }
}
