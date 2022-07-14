package com.foodapp.eatme.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.foodapp.eatme.R;
import com.foodapp.eatme.util.LocaleHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = preferences.getString("languageKey", null);
        if (lang == null) {
            lang = Locale.getDefault().getLanguage();
        }
        Handler handler = new Handler();
        handler.postDelayed(this::nextActivity, 3000);
        Log.e("Lang", lang);
        setAppLocale(this, lang);
        setContentView(R.layout.activity_splash);
    }

    private void setAppLocale(Context context, String language) {
        LocaleHelper.context = this;
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent;
        if (user == null) {
            intent = new Intent(this, SignInActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        finish();
        startActivity(intent);
    }
}