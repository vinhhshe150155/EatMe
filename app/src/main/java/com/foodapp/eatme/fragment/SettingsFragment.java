package com.foodapp.eatme.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.SplashActivity;
import com.foodapp.eatme.util.LocaleHelper;

import java.util.Locale;

public class SettingsFragment extends PreferenceFragmentCompat {
    DrawerLayout drawerLayout;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
        ListPreference languages = findPreference("languageKey");
        String langCurrent = String.valueOf(languages.getValue());
        if (langCurrent.trim().equals("") || langCurrent.trim().equals("null")) {
            langCurrent = LocaleHelper.getCurrentLanguage();
        }
        languages.setSummary(langCurrent.equals("ko") ? "한국어" : "English");
        String finalLangCurrent = langCurrent;
        languages.setOnPreferenceChangeListener((preference, newValue) -> {
            String oldLang = finalLangCurrent;
            if (!newValue.equals(oldLang)) {
                setAppLocale(requireContext(), String.valueOf(newValue));
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                getActivity().finishAffinity();
                startActivity(intent);
            }
            return true;
        });
        Preference pref = findPreference("keyMenu");
        pref.setOnPreferenceClickListener(preference -> {
            if (getActivity() != null) {
                drawerLayout = getActivity().findViewById(R.id.drawerlayout);
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        });
    }

    private void setAppLocale(Context context, String language) {
        LocaleHelper.context = requireContext();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}