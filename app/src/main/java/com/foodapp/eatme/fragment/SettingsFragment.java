package com.foodapp.eatme.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.SplashActivity;
import com.foodapp.eatme.util.LocaleHelper;

public class SettingsFragment extends PreferenceFragmentCompat{
    Context context;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
        ListPreference languages = findPreference("languageKey");
        languages.setSummary(languages.getEntry());
        languages.setOnPreferenceChangeListener((preference, newValue) -> {
            String oldLang = LocaleHelper.getCurrentLanguage();
            context = LocaleHelper.setLocale(requireContext(), String.valueOf(newValue));
            int indexOfValue = languages.findIndexOfValue(String.valueOf(newValue));
            languages.setSummary(indexOfValue >= 0 ? languages.getEntries()[indexOfValue] : null);
            if(!newValue.equals(oldLang)){
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                getActivity().finishAffinity();
                startActivity(intent);
            }
            return true;
        });
    }
}