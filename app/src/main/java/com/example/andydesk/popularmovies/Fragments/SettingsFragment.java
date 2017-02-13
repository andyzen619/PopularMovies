package com.example.andydesk.popularmovies.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.example.andydesk.popularmovies.R;

import java.util.prefs.PreferenceChangeListener;

/**
 * Created by owner on 07/02/2017.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("preference_sort_by_rating")) {
            //Obtatin a reference to the checkBox reference
            CheckBoxPreference checkBoxPreference= (CheckBoxPreference) findPreference(key);
            checkBoxPreference.setChecked(sharedPreferences.getBoolean(key, false));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //Registers a call back for when the listener is invoked
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}


