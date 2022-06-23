package com.foodapp.eatme.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.foodapp.eatme.adapter.RecipeStepAdapter;
import com.foodapp.eatme.model.Step;

import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "languageKey";
    public final static String LANG_KR = "ko";
    public final static String LANG_EN = "en";
    public static Context context;
    public static Context setLocale(Context context, String language) {
        persist(context, language);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }
        return updateResourcesLegacy(context, language);
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }


    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    public static String getCurrentLanguage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = preferences.getString(SELECTED_LANGUAGE, null);
        if (lang == null || lang.equals("")) {
            lang = Locale.getDefault().getLanguage();
        }
        return lang;
    }
}

