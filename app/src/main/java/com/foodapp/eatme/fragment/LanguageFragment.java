package com.foodapp.eatme.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.foodapp.eatme.R;
import com.foodapp.eatme.activity.SplashActivity;
import com.foodapp.eatme.util.LocaleHelper;


public class LanguageFragment extends Fragment {
    Context context;
    Resources resources;
    private Button btnEnglish;
    private Button btnKor;
    private Button btnTest;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        btnEnglish = view.findViewById(R.id.btn_english);
        btnKor = view.findViewById(R.id.btn_kOr);
        btnTest = view.findViewById(R.id.btn_test);
        bindingAction();
        return view;
    }

    private void bindingAction(){
        btnEnglish.setOnClickListener(view -> {
            context = LocaleHelper.setLocale(requireContext(), "en");
            resources = context.getResources();
            Intent intent = new Intent(getActivity(), SplashActivity.class);
            startActivity(intent);
        });
        btnKor.setOnClickListener(view -> {
            context = LocaleHelper.setLocale(requireContext(), "ko");
            resources = context.getResources();
            Intent intent = new Intent(getActivity(), SplashActivity.class);
            getActivity().finishAffinity();
            startActivity(intent);
        });
    }

}