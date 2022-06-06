package com.vinhhs.afood;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = preferences.getString("Locale.Helper.Selected.Language", null);
        if (lang == null) {
            lang = Locale.getDefault().getLanguage();
        }
//        Locale locale = new Locale(lang);
//        Locale.setDefault(locale);
//        Configuration config = this.resources.getConfiguration();
//        config.setLocale(locale);
//        this.createConfigurationContext(config);
//        this.resources.updateConfiguration(config, this.resources.getDisplayMetrics());
//        context = LocaleHelper.setLocale(this, lang);
//        resources = context.getResources();
        Handler handler = new Handler();
        handler.postDelayed(this::nextActivity, 1000);
        setAppLocale(this, lang);
        setContentView(R.layout.activity_splash);


    }
    private void setAppLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(this, SignInActivity.class);
            finish();
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }
        finish();
    }
}