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

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
